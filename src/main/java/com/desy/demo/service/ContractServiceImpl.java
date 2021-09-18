package com.desy.demo.service;

import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.payloads.request.ContractRequest;
import com.desy.demo.data.payloads.response.MessageResponse;
import com.desy.demo.repository.ContractRepository;
import org.springframework.stereotype.Service;

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
        return new MessageResponse("New Item created successfully");
    }
}
