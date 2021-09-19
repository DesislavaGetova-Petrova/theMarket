package com.desy.demo.controller;

import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/find/{id}")
    public ResponseEntity<UserEntity> getUserById (@PathVariable("id") Integer id) {
        UserEntity user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/allItems/{id}")
    public ResponseEntity<List<ItemEntity>> getItemsById (@PathVariable("id") Integer id) {
        List<ItemEntity> items = userService.findItemsById(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


}

