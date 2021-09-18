package com.desy.demo.data.payloads.request;

public class ContractRequest {
    private int item;
    private double price;

    public ContractRequest() {
    }

    public int getItem() {
        return item;
    }

    public ContractRequest setItem(int item) {
        this.item = item;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ContractRequest setPrice(double price) {
        this.price = price;
        return this;
    }
}
