package com.example.finalproject.controller;

import com.example.finalproject.models.ReviewModel;
import com.example.finalproject.models.UserModel;
import com.example.finalproject.repositories.ReviewRepository;
import com.example.finalproject.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class reviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/main/products/reviews")
    public String productsReviews(Model model,
                                  @RequestParam int productID) {
        List<ReviewModel> reviews = reviewRepository.findAll();
        List<UserModel> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("reviews", reviews);
        model.addAttribute("productID", productID);
        return "reviews";
    }

    @PostMapping("/main/products/reviews/add")
    public String addReview(
            @RequestParam int productID,
            @RequestParam int rating,
            @RequestParam String comment,
            HttpSession session, Model model
            ) {
        UserModel user = (UserModel) session.getAttribute("currentUser");
        ReviewModel review = new ReviewModel(user.getId(), productID, rating, comment);
        reviewRepository.save(review);
        return "redirect:/main/products";
    }
}
