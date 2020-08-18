package com.plantify.cart.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.plantify.cart.model.Cart;
import com.plantify.cart.model.Catalogue;
import com.plantify.cart.model.Login;

public interface CartService {
	public Cart createCart(Cart cart);

	public Optional<Cart> getById(int id);
	
	public List<Cart> getAll();

	public Cart getByProductName(String name);

	public CompletableFuture<Login> getUser(String name);

	public CompletableFuture<Catalogue> getEntity(int id);

	public void deleteCart(int id);
}
