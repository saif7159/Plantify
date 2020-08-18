package com.plantify.catalogue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plantify.catalogue.model.Catalogue;
@Repository
public interface CatalogueDao extends JpaRepository<Catalogue, Integer> {
	public List<Catalogue> findByCategory(String category);
	public Catalogue findByName(String name);

}
