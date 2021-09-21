package com.desy.demo.service;

import com.desy.demo.data.loads.request.AddContractRequest;
import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.repository.ContractRepository;
import com.desy.demo.repository.ItemRepository;
import com.desy.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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
    @Test
    void getAllActiveContractsTest() {
        ContractEntity contract1=new ContractEntity();
        contract1.setPrice(10);
        contract1.setStatus(true);
        contract1.setItem(new ItemEntity(){{setId(1);}});
        contract1.setBuyer(new UserEntity(){{setId(1);}});
        contract1.setSeller(new UserEntity(){{setId(2);}});

        ContractEntity contract2=new ContractEntity();
        contract2.setPrice(10);
        contract2.setStatus(false);
        contract2.setItem(new ItemEntity(){{setId(2);}});
        contract2.setBuyer(new UserEntity(){{setId(2);}});
        contract2.setSeller(new UserEntity(){{setId(1);}});

        Mockito.when(mockContractRepository.findAllByStatusOrderByPrice(true)).thenReturn(List.of(contract1));
        Assertions.assertEquals(1,contractService.getAllActiveContracts().size());
    }
    @Test
    void getAllContractsBySellerId() {
        ContractEntity contract1=new ContractEntity();
        contract1.setPrice(10);
        contract1.setStatus(true);
        contract1.setItem(new ItemEntity(){{setId(1);}});
        contract1.setBuyer(new UserEntity(){{setId(1);}});
        contract1.setSeller(new UserEntity(){{setId(2);}});
        Mockito.when(mockContractRepository.findAllBySellerId(1)).thenReturn(List.of(contract1));
        Assertions.assertEquals(1,contractService.getAllContractsBySellerId(1).size());
    }
    @Test
    void createContactTest() throws Exception {

        UserEntity userEntity1=new UserEntity();
        userEntity1.setAccount(100.00);
        userEntity1.setUsername("user1");
        userEntity1.setId(1);

        ItemEntity itemEntity1=new ItemEntity();
        itemEntity1.setId(1);
        itemEntity1.setName("item1");

        List<ItemEntity>items=new ArrayList<>();
        items.add(itemEntity1);
        userEntity1.setItems(items);

        itemEntity1.setOwner(userEntity1);

        ContractEntity contract1=new ContractEntity();
        contract1.setId(1);
        contract1.setPrice(10);
        contract1.setStatus(true);
        contract1.setItem(itemEntity1);
        mockContractRepository.save(contract1);

        AddContractRequest addContractRequest1=new AddContractRequest();
        addContractRequest1.setItem(2);
        addContractRequest1.setPrice(10.00);

        Assertions.assertThrows(
                NullPointerException.class, () -> {
                    contractService.createContract(addContractRequest1);
                }
        );
    }


}
