package com.desy.demo.service;

import com.desy.demo.repository.ContractRepository;
import com.desy.demo.repository.ItemRepository;
import com.desy.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ContractServiceTest {
    private ContractServiceImpl contractService;

    @Mock
    ContractRepository mockContractRepository;
    @Mock
    UserRepository mockUserRepository;
    @Mock
    ItemRepository mockItemRepository;
    @Mock
    ItemService mockItemService;
    @Mock
    UserService mockUserService;

    @BeforeEach
    public  void init() {
        contractService = new ContractServiceImpl(mockContractRepository,mockUserRepository,mockItemRepository,mockItemService,mockUserService);
    }
}
