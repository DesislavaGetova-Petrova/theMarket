package com.desy.demo.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class ItemEntity  {

    private int id;
    private String name;
    private UserEntity owner;

    public ItemEntity() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public int getId() {
        return id;
    }

    public ItemEntity setId(int id) {
        this.id = id;
        return this;
    }

    @Column
    public String getName() {
        return name;
    }

    public ItemEntity setName(String name) {
        this.name = name;
        return this;
    }
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity getOwner() {
        return owner;
    }

    public ItemEntity setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }
}
