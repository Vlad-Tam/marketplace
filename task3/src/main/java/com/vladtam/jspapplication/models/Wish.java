package com.vladtam.jspapplication.models;

import java.util.Objects;

public class Wish {
    private User user = new User();
    private Advertisement advertisement = new Advertisement();

    public Wish(){}

    public Wish(User user, Advertisement advertisement) {
        this.user = user;
        this.advertisement = advertisement;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wish wish = (Wish) o;
        return Objects.equals(user, wish.user) && Objects.equals(advertisement, wish.advertisement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, advertisement);
    }
}
