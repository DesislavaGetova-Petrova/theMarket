package com.desy.demo.data.loads.request;

public class AddItemRequest {
    private String name;
    private int owner;

    public AddItemRequest() {
    }

    public String getName() {
        return name;
    }

    public AddItemRequest setName(String name) {
        this.name = name;
        return this;
    }

    public int getOwner() {
        return owner;
    }

    public AddItemRequest setOwner(int owner) {
        this.owner = owner;
        return this;
    }
}
