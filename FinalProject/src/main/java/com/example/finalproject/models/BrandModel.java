package com.example.finalproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Brands")
public class BrandModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BrandID")
    private int Id;

    private String name;

    private String description;

    public BrandModel() {
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

    public BrandModel(String brandName, String description) {
        this.name = brandName;
        this.description = description;
    }
}
