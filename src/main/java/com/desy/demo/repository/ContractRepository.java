package com.desy.demo.repository;

import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.model.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity,Integer> {

    Optional<ContractEntity> findById(int id);
    Optional<ContractEntity> findByItem_Id(int id);

    Optional<ContractEntity> findByStatusAndItem_Id(boolean status,int id);

    List<ContractEntity>findAllByStatusOrderByPrice(boolean status);

    List<ContractEntity>findAllBySellerId(int id);
}
