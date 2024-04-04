package com.vladtam.springboot.entities;

import jakarta.persistence.*;

import java.util.Set;


@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private BasicAdvertisementInfo basicInfo = new BasicAdvertisementInfo();

    private Boolean saleStatus;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "wish_list",
            joinColumns = @JoinColumn(name = "id_advertisement"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private Set<User> wishingPeopleList;

    @ManyToOne
    @JoinColumn(name = "id_vendor")
    private User vendor;

    public Advertisement() {
    }

    public Advertisement(BasicAdvertisementInfo basicInfo, Boolean saleStatus, Set<User> wishingPeopleList) {
        this.basicInfo = basicInfo;
        this.saleStatus = saleStatus;
        this.wishingPeopleList = wishingPeopleList;
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

    public Set<User> getWishingPeopleList() {
        return wishingPeopleList;
    }

    public void setWishingPeopleList(Set<User> wishingPeopleList) {
        this.wishingPeopleList = wishingPeopleList;
    }

    public User getVendor() {
        return vendor;
    }

    public void setVendor(User vendor) {
        this.vendor = vendor;
    }
}


