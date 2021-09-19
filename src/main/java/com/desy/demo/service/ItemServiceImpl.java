package com.desy.demo.service;

import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.loads.request.AddItemRequest;
import com.desy.demo.data.loads.response.MessageResponse;
import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.repository.ItemRepository;
import com.desy.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private final UserService userService;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemServiceImpl(UserService userService, ItemRepository itemRepository, UserRepository userRepository) {
        this.userService = userService;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MessageResponse createItem(AddItemRequest addItemRequest) throws Exception {
        if(itemRepository.findByName(addItemRequest.getName()).isPresent()){
            throw new Exception("Item with this name already exist");
        }

            ItemEntity newItemEntity = new ItemEntity();
            newItemEntity.setName(addItemRequest.getName());
            newItemEntity.setOwner(userService.findById(addItemRequest.getOwner()));
            itemRepository.save(newItemEntity);

//            Optional<UserEntity> userOwner=userRepository.findById(addItemRequest.getOwner());
//            userOwner.get().getItems().add(newItemEntity);
//            userRepository.saveAndFlush(userOwner.get());

            return new MessageResponse("New Item created successfully");


    }

    @Override
    public List<ItemEntity> findAllByOwnerId(int id) {
        return this.itemRepository.findAllByOwnerId(id);
    }

    @Override
    public ItemEntity findById(int id) {
        return this.itemRepository.findById(id).orElse(null);
    }


}
