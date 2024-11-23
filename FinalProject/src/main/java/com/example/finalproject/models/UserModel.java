package com.example.finalproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class UserModel {
    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;

    }

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int Id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    private String username;
    private String password;


    public UserModel() {

    }
}
