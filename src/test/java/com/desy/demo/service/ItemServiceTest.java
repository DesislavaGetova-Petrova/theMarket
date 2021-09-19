package com.desy.demo.service;

import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
}
