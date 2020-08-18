package com.plantify.cart.client;

import org.springframework.stereotype.Component;

import com.plantify.cart.model.Catalogue;

@Component
public class CatalogueClientFallback implements CatalogueClient {

	@Override
	public Catalogue getEntityById(int id) {
		return new Catalogue();
	}

}
