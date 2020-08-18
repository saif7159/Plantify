package com.plantify.catalogue.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plantify.catalogue.dao.CatalogueDao;
import com.plantify.catalogue.model.Catalogue;
@Service
public class CatalogueServiceImpl implements CatalogueService{
	@Autowired
	private CatalogueDao dao;
	@Override
	public Catalogue createCatalogue(Catalogue catalogue) {
		return dao.save(catalogue);
	}

	@Override
	public Optional<Catalogue> getById(int id) {
		return dao.findById(id);
	}

	@Override
	public Catalogue getByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public List<Catalogue> getByCategory(String category) {
		return dao.findByCategory(category);
	}


	@Override
	public void deleteCatalogue(int id) {
		dao.deleteById(id);
	}

}
