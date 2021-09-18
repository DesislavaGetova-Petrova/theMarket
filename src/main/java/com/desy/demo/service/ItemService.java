package com.desy.demo.service;

import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.payloads.request.AddItemRequest;
import com.desy.demo.data.payloads.response.MessageResponse;

import java.util.List;

public interface ItemService {

    MessageResponse createItem(AddItemRequest addItemRequest);

    List<ItemEntity> findAllByOwnerId(int id);
    ItemEntity findById(int id);
}
