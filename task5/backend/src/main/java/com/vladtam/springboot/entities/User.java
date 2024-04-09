package com.vladtam.springboot.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user_account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private BaseUserInfo basicInfo = new BaseUserInfo();

    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @ManyToMany
    @JoinTable(name = "wish_list",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_advertisement"))
    private Set<Advertisement> wishList;

    @OneToMany(mappedBy = "vendor")
    private Set<Advertisement> salesList;

    @OneToMany(mappedBy = "receiver")
    private Set<Review> receivedCommentsList;

    @OneToMany(mappedBy = "sender")
    private Set<Review> sentCommentsList;

    public User() {
    }

    public User(BaseUserInfo baseUserInfo, Set<Advertisement> wishList, Set<Advertisement> salesList, Set<Review> receivedCommentsList, Set<Review> sentCommentsList) {
        this.basicInfo = baseUserInfo;
        this.wishList = wishList;
        this.salesList = salesList;
        this.receivedCommentsList = receivedCommentsList;
        this.sentCommentsList = sentCommentsList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BaseUserInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BaseUserInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Advertisement> getWishList() {
        return wishList;
    }

    public void setWishList(Set<Advertisement> wishList) {
        this.wishList = wishList;
    }

    public Set<Advertisement> getSalesList() {
        return salesList;
    }

    public void setSalesList(Set<Advertisement> salesList) {
        this.salesList = salesList;
    }

    public Set<Review> getReceivedCommentsList() {
        return receivedCommentsList;
    }

    public void setReceivedCommentsList(Set<Review> receivedCommentsList) {
        this.receivedCommentsList = receivedCommentsList;
    }

    public Set<Review> getSentCommentsList() {
        return sentCommentsList;
    }

    public void setSentCommentsList(Set<Review> sentCommentsList) {
        this.sentCommentsList = sentCommentsList;
    }

    @PrePersist
    protected void onCreate() {
        registrationDate = new Date();
    }
}


