package com.plantify.cart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plantify.cart.model.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
public Cart findByProductName(String name);
}
