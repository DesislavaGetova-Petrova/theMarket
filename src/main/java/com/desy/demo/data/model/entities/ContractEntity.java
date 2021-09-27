package com.desy.demo.data.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@org.hibernate.annotations.NamedNativeQuery(name = "ContractEntity.findByStatusAndItem_IdNew",
        query = "select c.id as id,c.price as price,c.status as status from contracts c where c.item=:id and c.status=:status",
        resultSetMapping = "Mapping.ContractProjection")
@org.hibernate.annotations.NamedNativeQuery(name = "ContractEntity.findAllByStatusOrderByPriceNew",
        query = "select c.id as id,c.price as price,c.status as status from contracts c where c.status=true order by c.price ",
        resultSetMapping = "Mapping.ContractProjection")
@org.hibernate.annotations.NamedNativeQuery(name = "ContractEntity.findAllBySellerIdNew",
        query = "select c.id as id,c.price as price,c.status as status from contracts c where c.seller=:id ",
        resultSetMapping = "Mapping.ContractProjection")
@SqlResultSetMapping(name = "Mapping.ContractProjection",
        classes = @ConstructorResult(targetClass = ContractProjection.class,
                columns = {@ColumnResult(name = "id"),
                        @ColumnResult(name = "price"),
                        @ColumnResult(name = "status")}))


@Entity
@Table(name = "contracts")
public class ContractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "seller", referencedColumnName = "id")
    private UserEntity seller;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "buyer", referencedColumnName = "id")
    private UserEntity buyer;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "item", referencedColumnName = "id")
    private ItemEntity item;
    @Column
    private double price;
    @Column
    private boolean status;

    public ContractEntity() {
    }

    public int getId() {
        return id;
    }

    public ContractEntity setId(int id) {
        this.id = id;
        return this;
    }


    public UserEntity getSeller() {
        return seller;
    }

    public ContractEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public ContractEntity setBuyer(UserEntity buyer) {
        this.buyer = buyer;
        return this;
    }

    public ItemEntity getItem() {
        return item;
    }

    public ContractEntity setItem(ItemEntity item) {
        this.item = item;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ContractEntity setPrice(double price) {
        this.price = price;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public ContractEntity setStatus(boolean status) {
        this.status = status;
        return this;
    }
}
