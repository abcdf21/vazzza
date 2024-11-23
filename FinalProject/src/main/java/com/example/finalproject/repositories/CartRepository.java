package com.example.finalproject.repositories;

import com.example.finalproject.models.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartModel, Integer> {
}
