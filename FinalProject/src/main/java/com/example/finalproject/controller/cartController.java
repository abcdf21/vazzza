package com.example.finalproject.controller;

import com.example.finalproject.models.CartModel;
import com.example.finalproject.models.ProductModel;
import com.example.finalproject.models.UserModel;
import com.example.finalproject.repositories.CartRepository;
import com.example.finalproject.repositories.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class cartController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;


    @GetMapping("/main/cart")
    public String mainCart(Model model, HttpSession session) {
        List<CartModel> carts = cartRepository.findAll();
        List<ProductModel> products = productRepository.findAll();
        UserModel user = (UserModel) session.getAttribute("currentUser");
        int userId = user.getId();

        model.addAttribute("carts", carts);
        model.addAttribute("products", products);
        model.addAttribute("userId", userId);

        return "cart";
    }

    @PostMapping("/main/cart/add")
    public String addItem(
            @RequestParam int productID,
            HttpSession session
    ){
        UserModel user = (UserModel) session.getAttribute("currentUser");
        ProductModel product = (ProductModel) productRepository.findById(productID).get();

        int quantity = 1;
        double price = product.getPrice();

        List<CartModel> carts = cartRepository.findAll();
        for (CartModel cart : carts) {
            if (cart.getUserid() == user.getId() && cart.getProductid() == productID)  {
                quantity = cart.getQuantity() + 1;
                price = product.getPrice() * quantity;
            }
        }
        CartModel cartItem = new CartModel(user.getId(), productID, quantity, price);
        cartRepository.save(cartItem);
        return "redirect:/main/products";
    }

    @PostMapping("/main/cart/updProduct")
    public String updProductItem(
            @RequestParam int cartid,
            @RequestParam String action
    )
    {
        CartModel cartItem = cartRepository.findById(cartid).get();

        double price = cartItem.getPrice()/cartItem.getQuantity();
        if (action.equals("+")) {

            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setPrice(price* cartItem.getQuantity());
            cartItem.setId(cartid);
            cartRepository.save(cartItem);
        }
        else{
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItem.setPrice(price*cartItem.getQuantity());
            cartItem.setId(cartid);
            if (cartItem.getQuantity() == 0) {
                cartRepository.deleteById(cartid);
            }
            else{
                cartRepository.save(cartItem);
            }

        }
        return "redirect:/main/cart";
    }
}
