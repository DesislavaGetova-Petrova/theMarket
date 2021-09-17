package com.desy.demo.model.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="users")
public class UserEntity {
    private int id;
    private String username;
    private Double account;
    private List<ItemEntity> items=new LinkedList<>();

    public UserEntity() {
    }
    @Id
    @Column
    public int getId() {
        return id;
    }

    public UserEntity setId(int id) {
        this.id = id;
        return this;
    }

    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }
    @Column
    public Double getAccount() {
        return account;
    }

    public UserEntity setAccount(Double account) {
        this.account = account;
        return this;
    }

    @OneToMany(mappedBy = "owner",targetEntity = ItemEntity.class, fetch = FetchType.EAGER,cascade= {CascadeType.ALL})
    public List<ItemEntity> getItems() {
        return items;
    }

    public UserEntity setItems(List<ItemEntity> items) {
        this.items = items;
        return this;
    }
}