package com.desy.demo.repository;

import com.desy.demo.data.model.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Integer> {

    List<ItemEntity>findAllByOwnerId(int id);

    Optional<ItemEntity>findById(int id);

    Optional<ItemEntity>findByName(String name);

}
