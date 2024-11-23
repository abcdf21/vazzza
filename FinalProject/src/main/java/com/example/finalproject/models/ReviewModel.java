package com.example.finalproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Reviews")
public class ReviewModel {
    public ReviewModel() {

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

    public ReviewModel(int userid, int productid, int rating, String comment) {
        this.userid = userid;
        this.productid = productid;
        this.rating = rating;
        this.comment = comment;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID")
    private int Id;

    @Column (name = "UserID")
    private int userid;

    @Column (name = "ProductID")
    private int productid;

    private int rating;
    private String comment;
}
