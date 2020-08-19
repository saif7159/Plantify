package com.plantify.catalogue;

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

import com.plantify.catalogue.model.Catalogue;
import com.plantify.catalogue.service.CatalogueService;

@SpringBootTest
@AutoConfigureMockMvc
public class CatalogueControllerTest {
	@MockBean
	private CatalogueService service;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testItemById() throws Exception {
		Mockito.when(service.getById(Mockito.anyInt()))
				.thenReturn(Optional.of(new Catalogue(1, "foxtail fern", "ferns", 200)));
		mockMvc.perform(get("/entity/id/1").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("foxtail fern"))).andExpect(jsonPath("$.category", is("ferns")))
				.andExpect(jsonPath("$.price", is(200)));

	}
}
