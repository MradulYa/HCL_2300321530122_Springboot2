package com.example.myproject.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Customer user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cartitem> items = new ArrayList<>();

    // Constructors
    public Cart() {}

    public Cart(Customer user) {
        this.user = user;
    }


    public void addCartItem(Cartitem item) {
        items.add(item);
        item.setCart(this);}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Customer getUser() { return user; }
    public void setUser(Customer user) { this.user = user; }

    public List<Cartitem> getItems() { return items; }
    public void setItems(List<Cartitem> items) { this.items = items; }
}
