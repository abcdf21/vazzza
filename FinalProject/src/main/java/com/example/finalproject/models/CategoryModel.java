package com.example.finalproject.models;

import jakarta.persistence.*;

@Entity
@Table (name = "Categories")
public class CategoryModel {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private int Id;

    private String name;

    private String description;

    public CategoryModel() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryModel(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
