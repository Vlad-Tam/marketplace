package com.vladtam.jspapplication.models;

import java.util.Objects;
import java.util.Set;

public class Advertisement implements BaseModelInterface {
    private Integer id;
    private BasicAdvertisementInfo basicInfo = new BasicAdvertisementInfo();
    private Boolean saleStatus;
    private Category category;
    private User vendor;
    private Set<User> wishingPeopleList;

    public Advertisement(){}

    public Advertisement(Integer id, BasicAdvertisementInfo basicInfo, Boolean saleStatus, Category category, User vendor, Set<User> wishingPeopleList) {
        this.id = id;
        this.basicInfo = basicInfo;
        this.saleStatus = saleStatus;
        this.category = category;
        this.vendor = vendor;
        this.wishingPeopleList = wishingPeopleList;
    }

    public Advertisement(Integer id, BasicAdvertisementInfo basicInfo, Boolean saleStatus) {
        this.id = id;
        this.basicInfo = basicInfo;
        this.saleStatus = saleStatus;
    }

    public Advertisement(Integer id, String name, Double price) {
        this.id = id;
        this.basicInfo.setName(name);
        this.basicInfo.setPrice(price);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BasicAdvertisementInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicAdvertisementInfo basicInfo) {
        this.basicInfo = basicInfo;
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

    public Set<User> getWishingPeopleList() {
        return wishingPeopleList;
    }

    public void setWishingPeopleList(Set<User> wishingPeopleList) {
        this.wishingPeopleList = wishingPeopleList;
    }

    @Override
    public String toString() {
        return "id=" + id + " " + basicInfo.getName() + " " + basicInfo.getPrice();
    }

    @Override
    public String outputFullInfo() {
        return "ADVERTISEMENT INFO:\n" +
                "id = " + id + "\n" +
                "name = " + basicInfo.getName() + "\n" +
                "description = " + basicInfo.getDescription() + "\n" +
                "price = " + basicInfo.getPrice() + "\n" +
                "saleStatus = " + saleStatus + "\n" +
                "category = (" + category + ")\n" +
                "user = (" + vendor + ")\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return Objects.equals(id, that.id) && Objects.equals(basicInfo, that.basicInfo) && Objects.equals(saleStatus, that.saleStatus) && Objects.equals(category, that.category) && Objects.equals(vendor, that.vendor) && Objects.equals(wishingPeopleList, that.wishingPeopleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, basicInfo, saleStatus, category, vendor, wishingPeopleList);
    }
}
