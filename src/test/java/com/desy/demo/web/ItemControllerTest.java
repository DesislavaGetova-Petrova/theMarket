package com.desy.demo.web;

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
public class ItemControllerTest {
    private int testItemId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    private ItemTestData testData;

    @BeforeEach
    public void setup() {
        testData = new ItemTestData(userRepository,itemRepository);
        testData.init();
        testItemId = testData.getTestItemId();
    }
    @AfterEach
    public void tearDown() {
        testData.cleanUp();
    }

    @Test
    void testFindAllItemsByOwnerId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                "/items/find/{id}", testItemId
        )).
                andExpect(status().isOk());
    }
    @Test
    void testAddItemPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post( "/items/add")
                .param("mame", "item1")
                .param("owner","1"));

        Assertions.assertEquals(1, itemRepository.count());
    }

}
