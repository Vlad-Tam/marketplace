<<<<<<<< HEAD:src/main/java/com/vladtam/jspapplication/models/City.java
package com.vladtam.jspapplication.models;

import java.util.Objects;

public class City implements BaseModelInterface {
    private Integer id;
    private String name;
    private String region;

    public City(){}

    public City(Integer id, String name, String region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "id=" + id + ", г." + name + ", " + region + " обл.";
    }

    @Override
    public String outputFullInfo() {
        return "CITY INFO:\n" +
                "id = " + id + "\n" +
                "name = " + name + "\n" +
                "region = " + region + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) && Objects.equals(name, city.name) && Objects.equals(region, city.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, region);
    }
}
========
package com.vladtam.marketplace.models;

import java.util.Objects;

public class City implements BaseModelInterface {
    private Integer id;
    private String name;
    private String region;

    public City(){}

    public City(Integer id, String name, String region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "id=" + id + ", г." + name + ", " + region + " обл.";
    }

    @Override
    public String outputFullInfo() {
        return "CITY INFO:\n" +
                "id = " + id + "\n" +
                "name = " + name + "\n" +
                "region = " + region + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) && Objects.equals(name, city.name) && Objects.equals(region, city.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, region);
    }
}
>>>>>>>> main:task2/src/main/java/com/vladtam/marketplace/models/City.java
