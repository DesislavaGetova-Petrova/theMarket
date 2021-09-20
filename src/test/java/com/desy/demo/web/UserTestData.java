package com.desy.demo.web;

import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.repository.UserRepository;

import java.util.ArrayList;

public class UserTestData {
    private int testUserId;

    private UserRepository userRepository;

    public UserTestData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void init() {
        UserEntity userEntity=new UserEntity();
        userEntity.setId(1);
        userEntity.setUsername("user1");
        userEntity.setItems(new ArrayList<>());
        userEntity.setAccount(100.00);
        userEntity=userRepository.save(userEntity);

        testUserId=userEntity.getId();

    }
    void cleanUp() {
        userRepository.deleteAll();
    }

    public int getTestUserId() {
        return testUserId;
    }
}
