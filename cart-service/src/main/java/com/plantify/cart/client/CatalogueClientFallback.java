package com.plantify.cart.client;

import org.springframework.stereotype.Component;

import com.plantify.cart.model.Catalogue;

@Component
public class CatalogueClientFallback implements CatalogueClient {
	// Returning a new object so i can throw appropriate service down exception
	// in case of open circuit
	@Override
	public Catalogue getEntityById(int id) {
		return new Catalogue();
	}

}
