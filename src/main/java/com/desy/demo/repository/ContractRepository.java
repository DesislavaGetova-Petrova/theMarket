package com.desy.demo.repository;

import com.desy.demo.data.model.entities.ContractEntity;
import com.desy.demo.data.model.entities.ContractProjection;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity,Integer> {

    Optional<ContractEntity> findById(int id);
    Optional<ContractEntity> findByItem_Id(int id);



    @Query(nativeQuery = true)
    ContractProjection findByStatusAndItem_IdNew(boolean status,int id);

    Optional<ContractEntity> findByStatusAndItem_Id(boolean status,int id);




    @Query(nativeQuery = true,
            value = "select new com.desy.demo.data.model.entities.ContractProjection(c.price,c.status) from contracts as c where c.seller=:id")
    List<ContractProjection>findAllBySellerIdNew(int id);


    List<ContractEntity>findAllBySellerId(int id);




    @Query(nativeQuery = true,
            value = "select new com.desy.demo.data.model.entities.ContractProjection(c.id, c.price, c.status) from contracts as c where c.status=true order by c.price")
    List<ContractProjection>findAllByStatusOrderByPriceNew();


    List<ContractEntity>findAllByStatusOrderByPrice(boolean status);








}
