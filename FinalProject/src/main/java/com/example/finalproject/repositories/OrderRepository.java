package com.example.finalproject.repositories;

import com.example.finalproject.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Integer> {
}
