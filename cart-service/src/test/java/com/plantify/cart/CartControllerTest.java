package com.plantify.cart;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.plantify.cart.model.Cart;
import com.plantify.cart.service.CartService;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CartService service;

	@Test
	public void testGetCartById() throws Exception {
		Mockito.when(service.getById(Mockito.anyInt())).thenReturn(Optional.of(new Cart(1, 1, "Money Plant", "Climbers",
				150, "saif.24feb@gmail.com", "987654312", "ABC Street", "Delhi", 110045)));
		mockMvc.perform(get("/get-cart/id/1").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.productId", is(1)))
				.andExpect(jsonPath("$.productName", is("Money Plant")))
				.andExpect(jsonPath("$.productCategory", is("Climbers"))).andExpect(jsonPath("$.price", is(150)))
				.andExpect(jsonPath("$.email", is("saif.24feb@gmail.com")));
	}

}
