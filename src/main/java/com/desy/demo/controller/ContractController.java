package com.desy.demo.controller;

import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.payloads.request.ContractRequest;
import com.desy.demo.data.payloads.request.ItemRequest;
import com.desy.demo.data.payloads.response.MessageResponse;
import com.desy.demo.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    private final ContractService contractService;


    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ContractEntity>> getAllActiveContracts () {
        List<ContractEntity> activeContracts = contractService.getAllActiveContracts();
        return new ResponseEntity<>(activeContracts, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addContract(@RequestBody ContractRequest contractRequest) {
        MessageResponse newIContract = contractService.createContact(contractRequest);
        return new ResponseEntity<>(newIContract, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MessageResponse> updateContract( @PathVariable Integer id, @RequestBody ContractRequest contractRequest) throws Exception {
        MessageResponse updateContract = contractService.updateContract(id, contractRequest);
        return new ResponseEntity<>(updateContract, HttpStatus.OK);
    }

}
