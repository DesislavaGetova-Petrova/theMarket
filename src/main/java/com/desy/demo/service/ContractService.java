package com.desy.demo.service;

import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.loads.request.AddContractRequest;
import com.desy.demo.data.loads.request.CloseContractRequest;
import com.desy.demo.data.loads.request.UpdateContactRequest;
import com.desy.demo.data.loads.response.MessageResponse;

import java.util.List;

public interface ContractService {

    List<ContractEntity> getAllActiveContracts();

    MessageResponse createContract(AddContractRequest addContractRequest) throws Exception;

    MessageResponse updateContract(Integer id, UpdateContactRequest updateContactRequest) throws Exception;

    MessageResponse closeContract(Integer id, CloseContractRequest closeContractRequest) throws Exception;

    List<ContractEntity> getAllContractsBySellerId(int id);
}
