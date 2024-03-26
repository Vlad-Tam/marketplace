package com.vladtam.marketplace.models;

import java.util.Objects;

public class Category implements BaseModel{
    private Integer id;
    private String name;
    private String description;

    public Category(){}

    public Category(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public void setName(String categoryName) {
        this.name = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String categoryDescription) {
        this.description = categoryDescription;
    }

    @Override
    public String toString() {
        return "id=" + id + " " + name;
    }

    @Override
    public String outputFullInfo() {
        return "CATEGORY INFO:\n" +
                "id = " + id + "\n" +
                "name = " + name + "\n" +
                "description = " + description + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
