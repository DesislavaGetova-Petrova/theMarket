package com.desy.demo.service;
import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.model.entities.UserEntity;
import com.desy.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void seedUsers() {
        if(userRepository.count()==0){
            UserEntity user1 = new UserEntity().setUsername("user1").setAccount(100.00).setItems(new ArrayList<>()).setCurrency(Currency.getInstance(Locale.US));
            UserEntity user2 = new UserEntity().setUsername("user2").setAccount(200.00).setItems(new ArrayList<>()).setCurrency(Currency.getInstance(Locale.GERMANY));
            userRepository.saveAll(List.of(user1, user2));
        }
    }

    @Override
    public UserEntity findById(int id) {
        return userRepository
               .findById(id)
               .orElseThrow(
                       () -> new IllegalStateException("User not exist"));
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }



    @Override
    public List<ItemEntity> findItemsById(int id) {
        return userRepository.findById(id).get().getItems();
    }


}
