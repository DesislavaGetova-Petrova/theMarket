package com.desy.demo.repository;

import com.desy.demo.model.entities.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<ContractEntity,Integer> {
}
