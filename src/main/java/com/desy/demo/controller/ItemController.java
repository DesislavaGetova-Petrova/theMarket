package com.desy.demo.controller;

import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.loads.request.AddItemRequest;
import com.desy.demo.data.loads.response.MessageResponse;
import com.desy.demo.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

   private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addItem(@RequestBody AddItemRequest addItemRequest) throws Exception {
        MessageResponse newItem = itemService.createItem(addItemRequest);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }
    @GetMapping("find/{id}")
    public ResponseEntity<List<ItemEntity>> findAllItemsByOwnerId (@PathVariable("id") Integer id) {
        List<ItemEntity> items = itemService.findAllByOwnerId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


}
