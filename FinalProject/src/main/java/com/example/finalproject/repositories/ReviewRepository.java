package com.example.finalproject.repositories;

import com.example.finalproject.models.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewModel, Integer> {
}
