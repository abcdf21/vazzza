package com.example.finalproject.controller;

import com.example.finalproject.models.BrandModel;
import com.example.finalproject.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class brandController {

    @Autowired
    BrandRepository brandRepository;

    @GetMapping("/main/brands")
    public String brands(Model model) {
        List<BrandModel> brands = brandRepository.findAll();
        model.addAttribute("brands", brands);
        return "brands";
    }

    @PostMapping("/main/brands/create")
    public String create(
            @RequestParam String name,
            @RequestParam String description
    ) {
        BrandModel brand = new BrandModel(name, description);
        brandRepository.save(brand);
        return "redirect:/main/brands";
    }

    @PostMapping("/main/brands/update")
    public String update(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String description
    ){
        BrandModel brand = new BrandModel(name, description);
        brand.setId(id);
        brandRepository.save(brand);
        return "redirect:/main/brands";
    }

    @PostMapping("/main/brands/delete")
    public String delete(
            @RequestParam int id
    ){
        brandRepository.deleteById(id);
        return "redirect:/main/brands";
    }
}
