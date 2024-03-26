package com.vladtam.marketplace.models;

import java.util.Objects;

public class Advertisement implements BaseModelInterface {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Boolean saleStatus;
    private Category category;
    private User vendor;

    public Advertisement(){}

    public Advertisement(Integer id, String name, String description, Double price, Boolean saleStatus, Category category, User vendor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.saleStatus = saleStatus;
        this.category = category;
        this.vendor = vendor;
    }

    public Advertisement(Integer id, String name, String description, Double price, Boolean saleStatus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.saleStatus = saleStatus;
    }

    public Advertisement(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String advertisementName) {
        this.name = advertisementName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String advertisementDescription) {
        this.description = advertisementDescription;
    }

    public Boolean getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Boolean saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getVendor() {
        return vendor;
    }

    public void setVendor(User vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return "id=" + id + " " + name + " " + price;
    }

    @Override
    public String outputFullInfo() {
        return "ADVERTISEMENT INFO:\n" +
                "id = " + id + "\n" +
                "name = " + name + "\n" +
                "description = " + description + "\n" +
                "price = " + price + "\n" +
                "saleStatus = " + saleStatus + "\n" +
                "category = (" + category + ")\n" +
                "user = (" + vendor + ")\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(saleStatus, that.saleStatus) && Objects.equals(category, that.category) && Objects.equals(vendor, that.vendor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, saleStatus, category, vendor);
    }
}
