package com.desy.demo.web;

import com.desy.demo.repository.ContractRepository;
import com.desy.demo.repository.ItemRepository;
import com.desy.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ContractControllerTest {
    private int testContractId;
    private int testItemId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ContractRepository contractRepository;


    private ContractTestData testData;

    @BeforeEach
    public void setup() {
        testData = new ContractTestData(userRepository,itemRepository,contractRepository);
        testData.init();
        testContractId = testData.getTestContractId();
        testItemId=testData.getTestItemId();
    }
    @AfterEach
    public void tearDown() {
        testData.cleanUp();
    }

    @Test
    void testFindAllI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                "/contracts/all"
        )).
                andExpect(status().isOk());
    }
    @Test
    void testGetAllBySellerId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                "/contracts/allbySellerId/{id}", testContractId
        )).andExpect(status().isOk());
    }
    @Test
    void testAddContactPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post( "/contracts/add")
                .param("item", "1")
                .param("price","10"));

        Assertions.assertEquals(1, contractRepository.count());
    }



}
