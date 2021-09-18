package com.desy.demo.service;

import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.payloads.request.ContractRequest;
import com.desy.demo.data.payloads.response.MessageResponse;

import java.util.List;

public interface ContractService {
    MessageResponse createContact(ContractRequest contractRequest);

    MessageResponse updateContract(Integer id, ContractRequest contractRequest) throws Exception;

    List<ContractEntity> getAllActiveContracts();
}
