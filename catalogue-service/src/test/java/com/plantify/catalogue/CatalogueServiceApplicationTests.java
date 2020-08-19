package com.plantify.catalogue;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.plantify.catalogue.controller.CatalogueController;

@SpringBootTest
class CatalogueServiceApplicationTests {
	@Autowired
	private CatalogueController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
