package com.desy.demo.web;

import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.repository.ItemRepository;
import com.desy.demo.repository.UserRepository;

import java.util.ArrayList;

public class ItemTestData {
    private int testItemId;

    private UserRepository userRepository;
    private ItemRepository itemRepository;

    public ItemTestData(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
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

        testItemId=itemEntity.getId();

    }
    void cleanUp() {

        itemRepository.deleteAll();
        userRepository.deleteAll();
    }

    public int getTestItemId() {
        return testItemId;
    }
}
