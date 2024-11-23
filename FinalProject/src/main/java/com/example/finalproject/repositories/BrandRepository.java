package com.example.finalproject.repositories;

import com.example.finalproject.models.BrandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<BrandModel, Integer> {
    @Query(value = "SELECT * FROM Brands", nativeQuery = true)
    List<BrandModel> findAllBrands();
}
