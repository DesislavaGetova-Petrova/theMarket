package com.desy.demo.data.model.entities;




import java.io.Serializable;
import java.util.Objects;

public class ContractProjection implements Serializable {

    private int id;

    private double price;

    private boolean status;

    public ContractProjection(double price, boolean status) {
        this.price = price;
        this.status = status;
    }

    public ContractProjection(int id, double price, boolean status) {
        this.id = id;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public ContractProjection setId(int id) {
        this.id = id;
        return this;
    }



    public double getPrice() {
        return price;
    }

    public ContractProjection setPrice(double price) {
        this.price = price;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public ContractProjection setStatus(boolean status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractProjection that = (ContractProjection) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, status);
    }
}
