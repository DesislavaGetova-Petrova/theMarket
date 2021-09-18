package com.desy.demo.data.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(nullable = false)
    private String username;
    @Column
    private Double account;

    @JsonIgnore
    @OneToMany(mappedBy = "owner",targetEntity = ItemEntity.class, fetch = FetchType.EAGER,cascade= {CascadeType.ALL})
    private List<ItemEntity> items=new LinkedList<>();

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public UserEntity setId(int id) {
        this.id = id;
        return this;
    }


    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public Double getAccount() {
        return account;
    }

    public UserEntity setAccount(Double account) {
        this.account = account;
        return this;
    }


    public List<ItemEntity> getItems() {
        return items;
    }

    public UserEntity setItems(List<ItemEntity> items) {
        this.items = items;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ",account='"+ account+'\''+
                ", items=" + items +
                '}';
    }
}