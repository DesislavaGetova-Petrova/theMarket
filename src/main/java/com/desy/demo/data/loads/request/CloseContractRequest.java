package com.desy.demo.data.loads.request;

public class CloseContractRequest {
    private int buyer;

    public CloseContractRequest() {
    }

    public int getBuyer() {
        return buyer;
    }

    public CloseContractRequest setBuyer(int buyer) {
        this.buyer = buyer;
        return this;
    }
}
