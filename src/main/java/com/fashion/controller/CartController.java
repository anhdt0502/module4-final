package com.fashion.controller;

import com.fashion.session.CartSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam Long productId,
            HttpSession session) {

        CartSession cart =
                (CartSession) session.getAttribute("cart");

        if (cart == null) {
            cart = new CartSession();
            session.setAttribute("cart", cart);
        }

        cart.addProduct(productId);

        return "redirect:/products";
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session,
                           Model model) {

        CartSession cart =
                (CartSession) session.getAttribute("cart");

        if (cart == null) {
            cart = new CartSession();
        }

        model.addAttribute("cart", cart);

        return "cart";
    }
}