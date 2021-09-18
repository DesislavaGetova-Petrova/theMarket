package com.desy.demo.service;

import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.payloads.request.AddContractRequest;
import com.desy.demo.data.payloads.request.CloseContractRequest;
import com.desy.demo.data.payloads.request.UpdateContactRequest;
import com.desy.demo.data.payloads.response.MessageResponse;
import com.desy.demo.repository.ContractRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final ItemService itemService;
    private final UserService userService;

    public ContractServiceImpl(ContractRepository contractRepository, ItemService itemService, UserService userService) {
        this.contractRepository = contractRepository;
        this.itemService = itemService;
        this.userService = userService;
    }


    @Override
    public MessageResponse createContract(AddContractRequest addContractRequest) throws Exception {
        Optional<ContractEntity> existContract=contractRepository.findByItem_Id(addContractRequest.getItem());
        if((existContract.isEmpty())||(!existContract.get().isStatus())){
            ContractEntity contractEntity = new ContractEntity();
            contractEntity.setPrice(addContractRequest.getPrice());
            contractEntity.setStatus(true);
            contractEntity.setItem(itemService.findById(addContractRequest.getItem()));
            contractEntity.setSeller(itemService.findById(addContractRequest.getItem()).getOwner());
            contractRepository.save(contractEntity);
            return new MessageResponse("New Contract created successfully");
        }else{
            throw new Exception("Item already in contract ");
        }
    }

    @Override
    public MessageResponse updateContract(Integer id, UpdateContactRequest updateContactRequest) throws Exception {
        Optional<ContractEntity> contract=contractRepository.findByItem_Id(id);
        if (contract.isEmpty()) {
            throw new ResourceNotFoundException("Contract is not found");
        }
        else if(!contract.get().isStatus()){
            throw new Exception("Contract is not active!");
        }
        contract.get().setPrice(updateContactRequest.getPrice());
        contract.get().setStatus(true);
        contract.get().setItem(itemService.findById(id));
        contract.get().setSeller(itemService.findById(id).getOwner());
        contractRepository.save(contract.get());
        return new MessageResponse("Contract updated successfully");
    }

    @Override
    public List<ContractEntity> getAllActiveContracts() {
        return this.contractRepository.findAllByStatusOrderByPrice(true);
    }

    @Override
    public MessageResponse closeContract(Integer id, CloseContractRequest closeContractRequest) throws Exception {
       Optional<ContractEntity> contract=contractRepository.findByItem_Id(id);
       if(!contract.get().isStatus()){
           throw new Exception("Contract is not active!");
       }
       if((userService.findById(closeContractRequest.getBuyer()).getAccount())<(contract.get().getPrice())){
           throw new Exception("User not have enough money");
       }
       if(contract.get().getSeller().getId()==closeContractRequest.getBuyer()){
           throw new Exception(("The buyer and the owner is the same-operation can't complete successfully"));
       }
        contract.get().setStatus(false);
        contract.get().setItem(itemService.findById(id));
        contract.get().setSeller(userService.findById(itemService.findById(id).getOwner().getId()));
        contract.get().setBuyer(userService.findById(closeContractRequest.getBuyer()));
        contractRepository.save(contract.get());

        return new MessageResponse("Contract closed successfully");
    }


}
