package com.desy.demo.service;

import com.desy.demo.data.currency.MannysConverterAPI;
import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.data.loads.request.AddContractRequest;
import com.desy.demo.data.loads.request.CloseContractRequest;
import com.desy.demo.data.loads.request.UpdateContactRequest;
import com.desy.demo.data.loads.response.MessageResponse;
import com.desy.demo.repository.ContractRepository;
import com.desy.demo.repository.ItemRepository;
import com.desy.demo.repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final UserService userService;
    private final MannysConverterAPI mannysConverterAPI;

    public ContractServiceImpl(ContractRepository contractRepository, UserRepository userRepository, ItemRepository itemRepository, ItemService itemService, UserService userService, MannysConverterAPI mannysConverterAPI) {
        this.contractRepository = contractRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.userService = userService;
        this.mannysConverterAPI = mannysConverterAPI;
    }


    @Override
    public MessageResponse createContract(AddContractRequest addContractRequest) throws Exception {
        Optional<ContractEntity> existContract=contractRepository.findByStatusAndItem_Id(true,addContractRequest.getItem());
        if(existContract.isEmpty()){
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
        Optional<ContractEntity> contract=contractRepository.findByStatusAndItem_Id(true,id);
        if (contract.isEmpty()) {
            throw new ResourceNotFoundException("Contract is not found");
        }
        contract.get().setPrice(updateContactRequest.getPrice());
        contractRepository.save(contract.get());
        return new MessageResponse("Contract updated successfully");
    }

    @Override
    public List<ContractEntity> getAllActiveContracts() {
        return this.contractRepository.findAllByStatusOrderByPrice(true);
    }

    @Override
    public MessageResponse closeContract(Integer id, CloseContractRequest closeContractRequest) throws Exception {
       Optional<ContractEntity> contract=contractRepository.findByStatusAndItem_Id(true,id);
       double price=contract.get().getPrice();
       int sellerId=contract.get().getSeller().getId();
        if(contract.get().getSeller().getId()==closeContractRequest.getBuyer()){
            throw new Exception(("The buyer and the owner is the same-operation can't complete successfully"));
        }

       if((userService.findById(closeContractRequest.getBuyer()).getAccount())<(contract.get().getPrice())){
           throw new Exception("User not have enough money");
       }


        contract.get().setStatus(false);
        contract.get().setBuyer(userService.findById(closeContractRequest.getBuyer()));
        contractRepository.save(contract.get());

        Optional<UserEntity> userBuyer=userRepository.findById(closeContractRequest.getBuyer());
        Optional<UserEntity> userSeller=userRepository.findById(sellerId);
        double convertedPrice= mannysConverterAPI.rate(userSeller.get().getCurrency(),userBuyer.get().getCurrency())*price;
        userBuyer.get().setAccount(userService.findById(closeContractRequest.getBuyer()).getAccount()-convertedPrice);
        userRepository.saveAndFlush(userBuyer.get());

        Optional<ItemEntity>buyedItem=itemRepository.findById(id);
        buyedItem.get().setOwner(userService.findById(closeContractRequest.getBuyer()));
        itemRepository.saveAndFlush(buyedItem.get());


        userSeller.get().setAccount(userService.findById(sellerId).getAccount()+price);
        List<ItemEntity>itemsUpdate=userSeller.get().getItems();
        itemsUpdate.remove(itemService.findById(id));
        userSeller.get().setItems(itemsUpdate);
        userRepository.saveAndFlush(userSeller.get());

        return new MessageResponse("Contract closed successfully");
    }

    @Override
    public List<ContractEntity> getAllContractsBySellerId(int id) {
        return this.contractRepository.findAllBySellerId(id);
    }

}
