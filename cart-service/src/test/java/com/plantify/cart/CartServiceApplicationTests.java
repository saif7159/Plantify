package com.plantify.cart;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.plantify.cart.controller.CartController;

@SpringBootTest
class CartServiceApplicationTests {
	@Autowired
	private CartController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
