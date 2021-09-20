package com.desy.demo.web;

import com.desy.demo.repository.UserRepository;
import com.desy.demo.service.UserService;
import org.junit.jupiter.api.AfterEach;
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
public class UserControllerTest {

    private int testUserId;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private UserTestData testData;

    @BeforeEach
    public void setup() {
        testData = new UserTestData(userRepository);
        testData.init();
        testUserId = testData.getTestUserId();
    }
    @AfterEach
    public void tearDown() {
        testData.cleanUp();
    }

    @Test
    void testViewAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( "/users/all")).
                andExpect(status().isOk());
    }
    @Test
    void testGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                "/users/find/{id}", testUserId
        )).
                andExpect(status().isOk());
    }
}
