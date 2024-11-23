package com.example.finalproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Products")
public class ProductModel {
    public ProductModel() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private int Id;

    private String name;
    private Double price;

    @Column(name = "CategoryID")
    private int categoryid;

    public ProductModel(String name, Double price, int categoryid, int brandid) {
        this.name = name;
        this.price = price;
        this.categoryid = categoryid;
        this.brandid = brandid;
    }

    @Column(name = "BrandID")
    private int brandid;


}
