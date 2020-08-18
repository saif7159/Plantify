package com.plantify.catalogue.service;

import java.util.List;
import java.util.Optional;

import com.plantify.catalogue.model.Catalogue;

public interface CatalogueService {
	public Catalogue createCatalogue(Catalogue catalogue);

	public Optional<Catalogue> getById(int id);

	public Catalogue getByName(String name);

	public List<Catalogue> getByCategory(String category);

	public void deleteCatalogue(int id);
}
