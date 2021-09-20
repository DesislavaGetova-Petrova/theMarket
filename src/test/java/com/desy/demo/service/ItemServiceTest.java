package com.desy.demo.service;

import com.desy.demo.data.loads.request.AddContractRequest;
import com.desy.demo.data.loads.request.AddItemRequest;
import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    private ItemServiceImpl itemService;

    @Mock
    UserService mockUserService;
    @Mock
    ItemRepository mockItemRepository;

    @BeforeEach
    public  void init() {
        itemService = new ItemServiceImpl(mockUserService,mockItemRepository);
    }

    @Test
    void findItemByIdTest(){
        ItemEntity item=new ItemEntity();
        item.setId(1).setName("item1");
        Mockito.when(mockItemRepository.findById(1)).thenReturn(Optional.of(item));
        assertEquals("item1",itemService.findById(1).getName());
    }
    @Test
    void createItem() throws Exception {

        UserEntity userEntity1=new UserEntity();
        userEntity1.setAccount(100.00);
        userEntity1.setUsername("user1");
        userEntity1.setId(1);
        userEntity1.setItems(new ArrayList<>());

        ItemEntity itemEntity1=new ItemEntity();
        itemEntity1.setName("item1");
        itemEntity1.setOwner(userEntity1);
        mockItemRepository.save(itemEntity1);

        AddItemRequest addItemRequest=new AddItemRequest();
        addItemRequest.setName("item1");
        addItemRequest.setOwner(itemEntity1.getId());

        itemService.createItem( addItemRequest);
        Mockito.verify(mockItemRepository).save(itemEntity1);
    }
}
