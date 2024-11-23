package com.example.finalproject.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name = "Cart")
public class CartModel {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private int Id;

    public CartModel(int userid, int productid, int quantity, double price) {
        this.userid = userid;
        this.productid = productid;
        this.quantity = quantity;
        this.price = price;
    }

    @Column (name = "UserID")
    private int userid;

    @Column (name = "ProductID")
    private int productid;

    private int quantity;
    private double price;

    public CartModel() {

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
