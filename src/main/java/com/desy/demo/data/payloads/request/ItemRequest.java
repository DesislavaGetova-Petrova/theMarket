package com.desy.demo.data.payloads.request;

public class ItemRequest {
    private String name;
    private int owner;

    public ItemRequest() {
    }

    public String getName() {
        return name;
    }

    public ItemRequest setName(String name) {
        this.name = name;
        return this;
    }

    public int getOwner() {
        return owner;
    }

    public ItemRequest setOwner(int owner) {
        this.owner = owner;
        return this;
    }
}
