package com.desy.demo.repository;

import com.desy.demo.model.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<ItemEntity,Integer> {
}
