package com.desy.demo.repository;

import com.desy.demo.data.model.entities.ItemEntity;
import com.desy.demo.data.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findById(int id);

    List<UserEntity> findAll();

    @Query("select u.items from UserEntity as u ")
    List<List<ItemEntity>>findAllItems();
}
