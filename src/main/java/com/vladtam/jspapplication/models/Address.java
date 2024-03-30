package com.vladtam.jspapplication.models;

import java.util.Objects;

public class Address implements BaseModelInterface {
    private Integer id;
    private City city;
    private String street;
    private Integer houseNumber;
    private Integer flatNumber;

    public Address(){}

    public Address(Integer id, City city, String street, Integer houseNumber, Integer flatNumber) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

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

    @Override
    public String toString() {
        return "id=" + id + " " + city.getRegion() + " обл., г." + city.getName() + ", ул." + street + " д." + houseNumber + ", кв." + flatNumber;
    }

    @Override
    public String outputFullInfo() {
        return "ADDRESS INFO:\n" +
                "id = " + id + "\n" +
                "city_id = " + city.getId() + "\n" +
                "region = " + city.getRegion() + "\n" +
                "city = " + city.getName() + "\n" +
                "street = " + street + "\n" +
                "houseNumber = " + houseNumber + "\n" +
                "flatNumber = " + flatNumber + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(flatNumber, address.flatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, houseNumber, flatNumber);
    }
}
