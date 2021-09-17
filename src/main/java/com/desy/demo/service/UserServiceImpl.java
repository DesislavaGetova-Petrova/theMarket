package com.desy.demo.service;

import com.desy.demo.model.entities.UserEntity;
import com.desy.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void seedUsers() {
        if(userRepository.count()==0){
            UserEntity user1 = new UserEntity().setUsername("user1").setAccount(100.00).setItems(new ArrayList<>());
            UserEntity user2 = new UserEntity().setUsername("user2").setAccount(200.00).setItems(new ArrayList<>());

            userRepository.saveAll(List.of(user1, user2));

        }

    }
}
