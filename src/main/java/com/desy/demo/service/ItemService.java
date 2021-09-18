package com.desy.demo.service;

import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.data.payloads.request.ItemRequest;
import com.desy.demo.data.payloads.response.MessageResponse;

import java.util.List;

public interface ItemService {

    MessageResponse createItem(ItemRequest itemRequest);
    List<ItemEntity> findAllByOwnerId(int id);
}
