package com.desy.demo.service;

import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.payloads.request.ContractRequest;
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
    public MessageResponse createContact(ContractRequest contractRequest) {
        ContractEntity contractEntity=new ContractEntity();
        contractEntity.setPrice(contractRequest.getPrice());
        contractEntity.setStatus(true);
        contractEntity.setItem(itemService.findById(contractRequest.getItem()));
        contractEntity.setSeller(itemService.findById(contractRequest.getItem()).getOwner());
        contractRepository.save(contractEntity);
        return new MessageResponse("New Contract created successfully");
    }

    @Override
    public MessageResponse updateContract(Integer id, ContractRequest contractRequest) throws Exception {
        Optional<ContractEntity> contract=contractRepository.findById(id);
        if (contract.isEmpty()) {
            throw new ResourceNotFoundException("Contract is not found");
        }
        else if(!contract.get().isStatus()){
            throw new Exception("Contract is not active!");
        }
        contract.get().setPrice(contractRequest.getPrice());
        contract.get().setStatus(true);
        contract.get().setItem(itemService.findById(contractRequest.getItem()));
        contract.get().setSeller(itemService.findById(contractRequest.getItem()).getOwner());
        contractRepository.save(contract.get());
        return new MessageResponse("Contract updated successfully");
    }

    @Override
    public List<ContractEntity> getAllActiveContracts() {
        return this.contractRepository.findAllByStatusOrderByPrice(true);
    }


}
