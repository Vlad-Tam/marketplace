package com.vladtam.springboot.entities;

import com.vladtam.springboot.entities.primaryKeys.WishPK;
import jakarta.persistence.*;

@Entity
@Table(name = "wish_list")
@IdClass(WishPK.class)
public class Wish {
    @Id
    @Column(name = "id_user")
    private Integer userId;

    @Id
    @Column(name = "id_advertisement")
    private Integer advertisementId;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_advertisement", insertable = false, updatable = false)
    private Advertisement advertisement;

    public Wish() {
    }

    public Wish(Integer userId, Integer advertisementId, User user, Advertisement advertisement) {
        this.userId = userId;
        this.advertisementId = advertisementId;
        this.user = user;
        this.advertisement = advertisement;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Integer advertisementId) {
        this.advertisementId = advertisementId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
}