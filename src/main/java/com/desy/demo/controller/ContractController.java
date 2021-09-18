package com.desy.demo.controller;

import com.desy.demo.data.payloads.request.ContractRequest;
import com.desy.demo.data.payloads.request.ItemRequest;
import com.desy.demo.data.payloads.response.MessageResponse;
import com.desy.demo.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    private final ContractService contractService;


    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addContract(@RequestBody ContractRequest contractRequest) {
        MessageResponse newIContract = contractService.createContact(contractRequest);
        return new ResponseEntity<>(newIContract, HttpStatus.CREATED);
    }

}
