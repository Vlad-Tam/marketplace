package com.vladtam.jspapplication.models;

import java.util.Objects;

public class BasicAdvertisementInfo {
    private String name;
    private String description;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BasicAdvertisementInfo(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public BasicAdvertisementInfo(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicAdvertisementInfo that = (BasicAdvertisementInfo) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price);
    }
}
