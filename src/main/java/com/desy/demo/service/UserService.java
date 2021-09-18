package com.desy.demo.service;

import com.desy.demo.data.model.entities.UserEntity;

import java.util.List;

public interface UserService {
    void seedUsers();
    UserEntity findById(int id);
    List<UserEntity> findAll();


}
