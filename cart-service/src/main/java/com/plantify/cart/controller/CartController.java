package com.plantify.cart.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plantify.cart.model.Cart;
import com.plantify.cart.model.Catalogue;
import com.plantify.cart.model.Login;
import com.plantify.cart.service.CartService;
import com.plantify.exception.CartItemNotFoundException;

@RestController
public class CartController {
	@Autowired
	private CartService service;

	@PostMapping("/create-cart")
	public Cart createCartOrder(@RequestBody Cart cart) throws InterruptedException, ExecutionException {
		CompletableFuture<Login> login = service.getUser(cart.getEmail());
		CompletableFuture<Catalogue> catalogue = service.getEntity(cart.getProductId());
		CompletableFuture.allOf(login, catalogue).join();

		Login loginNew = login.get();
		Catalogue catalogueNew = catalogue.get();
		Cart newcart = new Cart();
		newcart.setProductId(cart.getProductId());
		newcart.setProductName(catalogueNew.getName());
		newcart.setProductCategory(catalogueNew.getCategory());
		newcart.setPrice(catalogueNew.getPrice());

		newcart.setEmail(loginNew.getEmail());
		newcart.setAddress(loginNew.getUserdetails().getAddress());
		newcart.setCity(loginNew.getUserdetails().getCity());
		newcart.setPhone(loginNew.getUserdetails().getPhone());
		newcart.setPin(loginNew.getUserdetails().getPin());
		return service.createCart(newcart);
	}

	@GetMapping("/get-all")
	public List<Cart> getAllEntities() {
		List<Cart> cart = service.getAll();
		if (cart.isEmpty())
			throw new CartItemNotFoundException("Cart is Empty");
		return cart;
	}

	@GetMapping("/get-cart/id/{id}")
	public Cart getCartEntity(@PathVariable int id) {
		Optional<Cart> cart = service.getById(id);
		if (cart.isEmpty())
			throw new CartItemNotFoundException("Item with " + id + " not found");
		return service.getById(id).get();
	}

	@GetMapping("/get-cart/name/{name}")
	public Cart getCartEntityByName(@PathVariable String name) {
		return service.getByProductName(name);
	}

	@DeleteMapping("/remove-cart/id/{id}")
	public void removeCart(@PathVariable int id) {
		Optional<Cart> cart = service.getById(id);
		if (cart.isEmpty())
			throw new CartItemNotFoundException("Item with " + id + " not found");
		service.deleteCart(id);
	}
}
