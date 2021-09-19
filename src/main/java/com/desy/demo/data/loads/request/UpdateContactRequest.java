package com.desy.demo.data.loads.request;

public class UpdateContactRequest {
    private double price;

    public UpdateContactRequest() {
    }

    public double getPrice() {
        return price;
    }

    public UpdateContactRequest setPrice(double price) {
        this.price = price;
        return this;
    }
}
