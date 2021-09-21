package com.desy.demo.web;

import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.repository.ContractRepository;
import com.desy.demo.repository.ItemRepository;
import com.desy.demo.repository.UserRepository;

import java.util.ArrayList;

public class ContractTestData {
    private int testContractId;
    private int testItemId;

    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private ContractRepository contractRepository;

    public ContractTestData(UserRepository userRepository, ItemRepository itemRepository, ContractRepository contractRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.contractRepository = contractRepository;
    }

    public void init() {
        UserEntity userEntity=new UserEntity();
        userEntity.setId(1);
        userEntity.setUsername("user1");
        userEntity.setItems(new ArrayList<>());
        userEntity.setAccount(100.00);
        userEntity=userRepository.save(userEntity);

        ItemEntity itemEntity=new ItemEntity();
        itemEntity.setId(1);
        itemEntity.setOwner(userEntity);
        itemEntity.setName("item1");
        itemEntity=itemRepository.save(itemEntity);

        ContractEntity contractEntity=new ContractEntity();
        contractEntity.setId(1);
        contractEntity.setStatus(true);
        contractEntity.setItem(itemEntity);
        contractEntity.setPrice(10.00);
        contractEntity=contractRepository.save(contractEntity);

        testContractId=contractEntity.getId();
        testItemId=itemEntity.getId();

    }
    void cleanUp() {

        contractRepository.deleteAll();
        itemRepository.deleteAll();
        userRepository.deleteAll();
    }

    public int getTestContractId() {
        return testContractId;
    }

    public int getTestItemId() {
        return testItemId;
    }
}
