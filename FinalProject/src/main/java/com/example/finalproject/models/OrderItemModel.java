package com.example.finalproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Orderdetails")
public class OrderItemModel {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderdetailID")
    private int Id;

    public OrderItemModel() {

    }

    public int getId() {
        return Id;
    }

    public OrderItemModel(int orderid, int productid, int quantity) {
        this.orderid = orderid;
        this.productid = productid;
        this.quantity = quantity;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "OrderID")
    private int orderid;

    @Column(name = "ProductID")
    private int productid;

    private int quantity;
}
