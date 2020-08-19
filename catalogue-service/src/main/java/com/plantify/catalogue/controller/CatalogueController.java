package com.plantify.catalogue.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plantify.catalogue.exception.ItemNotFoundException;
import com.plantify.catalogue.model.Catalogue;
import com.plantify.catalogue.service.CatalogueService;

@RestController
public class CatalogueController {
	@Autowired
	private CatalogueService service;

	@PostMapping("/create-entity")
	public Catalogue createEntity(@RequestBody Catalogue catalogue) {
		return service.createCatalogue(catalogue);
	}

	@GetMapping("/entity/id/{id}")
	public Catalogue getEntityById(@PathVariable int id) {
		Optional<Catalogue> catalogue = service.getById(id);
		if (catalogue.isEmpty())
			throw new ItemNotFoundException("No item with id: " + id);
		return catalogue.get();
	}

	@GetMapping("/entity/name/{name}")
	public Catalogue getEntityByName(@PathVariable String name) {
		return service.getByName(name);
	}

	@GetMapping("/entity/category/{category}")
	public List<Catalogue> getEntityByCategory(@PathVariable String category) {
		return service.getByCategory(category);
	}

	@PutMapping("/update-entity/id/{id}")
	public Catalogue updateEntity(@RequestBody Catalogue catalogue, @PathVariable int id) {
		Catalogue updateCatalogue = new Catalogue(id, catalogue.getName(), catalogue.getCategory(),
				catalogue.getPrice());
		return service.createCatalogue(updateCatalogue);
	}

	@DeleteMapping("/delete-entity/id/{id}")
	public void deleteEntity(@PathVariable int id) {
		Optional<Catalogue> catalogue = service.getById(id);
		if (catalogue.isEmpty())
			throw new ItemNotFoundException("No item with id: " + id);
		service.deleteCatalogue(id);
	}
}
