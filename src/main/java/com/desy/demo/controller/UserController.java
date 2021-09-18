package com.desy.demo.controller;

import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/all")
    public List<UserEntity> viewAll() {
        return this.userService.findAll();
    }







}

