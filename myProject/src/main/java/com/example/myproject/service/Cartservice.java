package com.example.myproject.service;

import com.example.myproject.model.Cart;
import com.example.myproject.model.Cartitem;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class Cartservice {
    private final Map<Long, Cart> activeCarts = new ConcurrentHashMap<>();

    public Cart getOrCreateCart(Long userId) {
        return activeCarts.computeIfAbsent(userId, id -> new Cart(id));
    }

    public Cart addItemToCart(Long userId, Long productId, int quantity) {
        Cart cart = getOrCreateCart(userId);


        Optional<com.example.myproject.model.Cartitem> existingItem = cart.getItems().stream()
                .filter(item -> {
                    return item.getProductId().equals(productId);
                })
                .findFirst();

        if (existingItem.isPresent()) {
            // Product exists -> Add to the existing quantity
            Cartitem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            // Product is new -> Append it to the items list
            cart.getItems().add(new Cartitem(productId, quantity));
        }

        return cart;
    }
}
