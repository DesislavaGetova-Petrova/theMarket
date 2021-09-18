package com.desy.demo.service;

import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.payloads.request.ItemRequest;
import com.desy.demo.data.payloads.response.MessageResponse;
import com.desy.demo.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private final UserService userService;
    private final ItemRepository itemRepository;

    public ItemServiceImpl(UserService userService, ItemRepository itemRepository) {
        this.userService = userService;
        this.itemRepository = itemRepository;
    }

    @Override
    public MessageResponse createItem(ItemRequest itemRequest) {
        ItemEntity newItemEntity=new ItemEntity();
        newItemEntity.setName(itemRequest.getName());
        newItemEntity.setOwner(userService.findById(itemRequest.getOwner()));
        itemRepository.save(newItemEntity);
        return new MessageResponse("New Item created successfully");


    }
}
