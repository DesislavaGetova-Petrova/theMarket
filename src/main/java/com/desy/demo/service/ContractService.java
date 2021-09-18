package com.desy.demo.service;

import com.desy.demo.data.payloads.request.ContractRequest;
import com.desy.demo.data.payloads.response.MessageResponse;

public interface ContractService {
    MessageResponse createContact(ContractRequest contractRequest);
}
