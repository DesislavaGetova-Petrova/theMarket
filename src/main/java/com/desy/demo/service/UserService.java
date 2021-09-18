package com.desy.demo.service;

import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.data.payloads.request.AddContractRequest;
import com.desy.demo.data.payloads.response.MessageResponse;

import java.util.List;

public interface UserService {

    void seedUsers();

    UserEntity findById(int id);

    List<UserEntity> findAll();

    MessageResponse createContract(AddContractRequest addContractRequest);




}
