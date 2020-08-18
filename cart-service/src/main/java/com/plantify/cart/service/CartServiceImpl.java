package com.plantify.cart.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.plantify.cart.client.CatalogueClient;
import com.plantify.cart.client.UserClient;
import com.plantify.cart.dao.CartDao;
import com.plantify.cart.model.Cart;
import com.plantify.cart.model.Catalogue;
import com.plantify.cart.model.Login;
import com.plantify.exception.CartItemNotFoundException;
import com.plantify.exception.ServiceDownException;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDao dao;
	@Autowired
	private UserClient userclient;
	@Autowired
	private CatalogueClient catalogueclient;

	@Override
	public Cart createCart(Cart cart) {
		return dao.save(cart);
	}

	@Override
	public Optional<Cart> getById(int id) {
		return dao.findById(id);
	}

	@Override
	public Cart getByProductName(String name) {
		return dao.findByProductName(name);
	}

	// Feign Calls for communicating with other services
	@Override
	@Async
	public CompletableFuture<Login> getUser(String name) {
		System.out.println("Thread name for user:" + Thread.currentThread().getName());
		Login login = userclient.getUserByEmail(name);
		if(login.getId()==0) throw new ServiceDownException("User Service is Down");
		return CompletableFuture.completedFuture(login);
	}

	@Override
	@Async
	public CompletableFuture<Catalogue> getEntity(int id) {
		System.out.println("Thread name for entity:" + Thread.currentThread().getName());
		Catalogue catalogue = catalogueclient.getEntityById(id);
		if(catalogue.getId()==0) throw new ServiceDownException("Catalogue Service is Down");
		System.out.println(catalogue.toString());
		return CompletableFuture.completedFuture(catalogue);
	}

	@Override
	public void deleteCart(int id) {
		dao.deleteById(id);
	}

	@Override
	public List<Cart> getAll() {
		return dao.findAll();
	}

}
