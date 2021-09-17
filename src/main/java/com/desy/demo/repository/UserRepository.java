package com.desy.demo.repository;

import com.desy.demo.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity,Integer> {
}
