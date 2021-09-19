package com.desy.demo.service;

import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.loads.request.AddItemRequest;
import com.desy.demo.data.loads.response.MessageResponse;

import java.util.List;

public interface ItemService {

    MessageResponse createItem(AddItemRequest addItemRequest) throws Exception;

    List<ItemEntity> findAllByOwnerId(int id);

    ItemEntity findById(int id);
}
