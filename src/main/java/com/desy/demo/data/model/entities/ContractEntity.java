package com.desy.demo.data.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "contracts")
public class ContractEntity {
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
