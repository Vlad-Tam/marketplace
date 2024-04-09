package com.vladtam.springboot.entities.pk;

import java.io.Serializable;
import java.util.Objects;

public class WishPK implements Serializable {
    private Integer userId;
    private Integer advertisementId;

    public WishPK() {
    }

    public WishPK(Integer userId, Integer advertisementId) {
        this.userId = userId;
        this.advertisementId = advertisementId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishPK wishPK = (WishPK) o;
        return Objects.equals(userId, wishPK.userId) && Objects.equals(advertisementId, wishPK.advertisementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, advertisementId);
    }
}
