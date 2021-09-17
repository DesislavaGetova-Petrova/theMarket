package com.desy.demo.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "contracts")
public class ContractEntity {
    private int id;
    private UserEntity seller;
    private UserEntity buyer;
    private ItemEntity item;
    private BigDecimal price;
    private boolean status;

    public ContractEntity() {
    }
    @Id
    @Column
    public int getId() {
        return id;
    }

    public ContractEntity setId(int id) {
        this.id = id;
        return this;
    }

    @OneToOne
    public UserEntity getSeller() {
        return seller;
    }

    public ContractEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }
    @OneToOne
    public UserEntity getBuyer() {
        return buyer;
    }

    public ContractEntity setBuyer(UserEntity buyer) {
        this.buyer = buyer;
        return this;
    }
    @OneToOne
    public ItemEntity getItem() {
        return item;
    }

    public ContractEntity setItem(ItemEntity item) {
        this.item = item;
        return this;
    }
    @Column
    public BigDecimal getPrice() {
        return price;
    }

    public ContractEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    @Column
    public boolean isStatus() {
        return status;
    }

    public ContractEntity setStatus(boolean status) {
        this.status = status;
        return this;
    }
}
