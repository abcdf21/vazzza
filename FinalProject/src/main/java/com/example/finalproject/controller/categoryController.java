package com.example.finalproject.controller;

import com.example.finalproject.models.BrandModel;
import com.example.finalproject.models.CategoryModel;
import com.example.finalproject.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class categoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/main/categories")
    public String brands(Model model) {
        List<CategoryModel> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @PostMapping("/main/categories/create")
    public String create(
            @RequestParam String name,
            @RequestParam String description
    ) {
        CategoryModel brand = new CategoryModel(name, description);
        categoryRepository.save(brand);
        return "redirect:/main/categories";
    }

    @PostMapping("/main/categories/update")
    public String update(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String description
    ){
        CategoryModel brand = new CategoryModel(name, description);
        brand.setId(id);
        categoryRepository.save(brand);
        return "redirect:/main/categories";
    }

    @PostMapping("/main/categories/delete")
    public String delete(
            @RequestParam int id
    ){
        categoryRepository.deleteById(id);
        return "redirect:/main/categories";
    }
}
