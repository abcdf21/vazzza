package com.example.finalproject.repositories;

import com.example.finalproject.models.OrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderItemModel, Integer> {
}
