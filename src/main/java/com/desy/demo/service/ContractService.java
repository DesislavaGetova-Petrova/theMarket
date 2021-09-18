package com.desy.demo.service;

import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.payloads.request.AddContractRequest;
import com.desy.demo.data.payloads.request.CloseContractRequest;
import com.desy.demo.data.payloads.request.UpdateContactRequest;
import com.desy.demo.data.payloads.response.MessageResponse;

import java.util.List;

public interface ContractService {
    MessageResponse createContract(AddContractRequest addContractRequest) throws Exception;

    MessageResponse updateContract(Integer id, UpdateContactRequest updateContactRequest) throws Exception;

    List<ContractEntity> getAllActiveContracts();

    MessageResponse closeContract(Integer id, CloseContractRequest closeContractRequest) throws Exception;
}
