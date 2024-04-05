package com.vladtam.springboot.entities;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_city")
    private City city;

    private String street;
    private Integer houseNumber;
    private Integer flatNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }
}
