package com.example.finalproject.repositories;

import com.example.finalproject.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {
}
