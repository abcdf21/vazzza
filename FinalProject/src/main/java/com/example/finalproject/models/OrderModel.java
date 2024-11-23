package com.example.finalproject.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Orders")
public class OrderModel {

    public OrderModel() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderModel(int userid, double price) {
        this.userid = userid;
        this.price = price;
        date = LocalDateTime.now();
    }

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private int Id;

    @Column (name = "UserID")
    private int userid;

    @Column(name = "Orderdate")
    private LocalDateTime date;

    @Column(name = "Totalamount")
    private double price;



}
