package com.example.finalproject.controller;

import com.example.finalproject.models.*;
import com.example.finalproject.repositories.CartRepository;
import com.example.finalproject.repositories.OrderDetailsRepository;
import com.example.finalproject.repositories.OrderRepository;
import com.example.finalproject.repositories.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ordersController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/main/pay")
    public String pay(HttpSession session) {
        UserModel user = (UserModel) session.getAttribute("currentUser");
        List<CartModel> carts = cartRepository.findAll();

        double price = 0;
        for (CartModel cart : carts) {
            if(cart.getUserid() == user.getId()) {
                price += cart.getPrice();
            }
        }

        OrderModel order = new OrderModel(user.getId(), price);
        orderRepository.save(order);
        for (CartModel cart : carts) {
            if(cart.getUserid() == user.getId()) {
                OrderItemModel orderItemModel = new OrderItemModel(order.getId(), cart.getProductid(), cart.getQuantity());
                orderDetailsRepository.save(orderItemModel);
                cartRepository.deleteById(cart.getId());
            }
        }



        return "redirect:/main/products";
    }

    @GetMapping("/main/orders")
    public String orders(HttpSession session, Model model) {
        List<OrderModel> orders = orderRepository.findAll();
        List<OrderItemModel> orderItems = orderDetailsRepository.findAll();
        List<ProductModel> products = productRepository.findAll();
        model.addAttribute("orders", orders);
        UserModel user = (UserModel) session.getAttribute("currentUser");
        model.addAttribute("userid", user.getId());
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("products", products);
        return "orders";
    }
}
