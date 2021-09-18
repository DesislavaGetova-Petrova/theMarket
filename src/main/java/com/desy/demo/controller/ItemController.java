package com.desy.demo.controller;

import com.desy.demo.data.payloads.request.ItemRequest;
import com.desy.demo.data.payloads.response.MessageResponse;
import com.desy.demo.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

   private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addItem(@RequestBody ItemRequest itemRequest) {
        MessageResponse newItem = itemService.createItem(itemRequest);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

}
