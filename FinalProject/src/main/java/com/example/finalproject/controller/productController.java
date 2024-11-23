package com.example.finalproject.controller;

import com.example.finalproject.models.BrandModel;
import com.example.finalproject.models.CategoryModel;
import com.example.finalproject.models.ProductModel;
import com.example.finalproject.repositories.BrandRepository;
import com.example.finalproject.repositories.CategoryRepository;
import com.example.finalproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class productController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/main/products")
    public String brands(Model model) {
        List<BrandModel> brands = brandRepository.findAll();
        List<CategoryModel> categories = categoryRepository.findAll();

        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        List<ProductModel> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/main/products/create")
    public String create(
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam int categoryid,
            @RequestParam int brandid
    ) {
        ProductModel brand = new ProductModel(name, price, categoryid, brandid);
        productRepository.save(brand);
        return "redirect:/main/products";
    }

    @PostMapping("/main/products/update")
    public String update(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam int categoryid,
            @RequestParam int brandid
    ){
        ProductModel brand = new ProductModel(name, price, categoryid, brandid);
        brand.setId(id);
        productRepository.save(brand);
        return "redirect:/main/products";
    }

    @PostMapping("/main/products/delete")
    public String delete(
            @RequestParam int id
    ){
        productRepository.deleteById(id);
        return "redirect:/main/products";
    }
}
