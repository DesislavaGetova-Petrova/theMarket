package com.desy.demo.data.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class ItemEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private UserEntity owner;

    public ItemEntity() {

    }

    public int getId() {
        return id;
    }

    public ItemEntity setId(int id) {
        this.id = id;
        return this;
    }


    public String getName() {
        return name;
    }

    public ItemEntity setName(String name) {
        this.name = name;
        return this;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public ItemEntity setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }
}
