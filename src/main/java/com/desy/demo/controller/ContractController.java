package com.desy.demo.controller;

import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.loads.request.AddContractRequest;
import com.desy.demo.data.loads.request.CloseContractRequest;
import com.desy.demo.data.loads.request.UpdateContactRequest;
import com.desy.demo.data.loads.response.MessageResponse;
import com.desy.demo.data.model.entities.ItemEntity;
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
    @GetMapping("/allbySellerId/{id}")
    public ResponseEntity<List<ContractEntity>> getAllBySellerId (@PathVariable("id") Integer id) {
        List<ContractEntity> contracts = contractService.getAllContractsBySellerId(id);
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addContract(@RequestBody AddContractRequest addContractRequest) throws Exception {
        MessageResponse newIContract = contractService.createContract(addContractRequest);
        return new ResponseEntity<>(newIContract, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MessageResponse> updateContract(@PathVariable Integer id, @RequestBody UpdateContactRequest updateContactRequest) throws Exception {
        MessageResponse updateContract = contractService.updateContract(id, updateContactRequest);
        return new ResponseEntity<>(updateContract, HttpStatus.OK);
    }
    @PutMapping("/close/{id}")
    public ResponseEntity<MessageResponse> closeContract(@PathVariable Integer id, @RequestBody CloseContractRequest closeContractRequest) throws Exception {
        MessageResponse closeContract = contractService.closeContract(id, closeContractRequest);
        return new ResponseEntity<>(closeContract, HttpStatus.OK);
    }

}
