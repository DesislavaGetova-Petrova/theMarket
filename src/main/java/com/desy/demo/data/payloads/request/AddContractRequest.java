package com.desy.demo.data.payloads.request;

public class AddContractRequest {
    private int item;
    private double price;

    public AddContractRequest() {
    }

    public int getItem() {
        return item;
    }

    public AddContractRequest setItem(int item) {
        this.item = item;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public AddContractRequest setPrice(double price) {
        this.price = price;
        return this;
    }
}
