package com.plantify.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.plantify.cart.model.Catalogue;

@FeignClient(name = "catalogue-service")
public interface CatalogueClient {
	@GetMapping("/entity/id/{id}")
	public Catalogue getEntityById(@PathVariable int id);
}
