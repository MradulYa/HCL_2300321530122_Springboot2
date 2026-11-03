package com.example.myproject.controller;

import com.example.myproject.model.Cart;
import com.example.myproject.service.Cartservice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final Cartservice cartService;

    public CartController(Cartservice cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getOrCreateCart(userId);
    }

    @PostMapping("/{userId}/add")
    public Cart addItem(
            @PathVariable Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return cartService.addItemToCart(userId, productId, quantity);
    }
}
