package com.desy.demo.repository;

import com.desy.demo.model.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,Integer> {
}
