package com.desy.demo.service;

import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserServiceImpl userService;
    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    public  void init() {
        userService=new UserServiceImpl(mockUserRepository);
    }
    @Test
    void findAllUserTest(){
        UserEntity user=new UserEntity();
        user.setUsername("user1").setAccount(100.00).setItems(new ArrayList<>());
        Mockito.when(mockUserRepository.findAll()).thenReturn(List.of(user));
        assertEquals(1,userService.findAll().size());
    }



}
