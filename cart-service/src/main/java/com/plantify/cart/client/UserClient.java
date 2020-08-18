package com.plantify.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.plantify.cart.model.Login;

@FeignClient(name = "user-service")
public interface UserClient {
	@GetMapping("auth/getUser/email/{email}")
	public Login getUserByEmail(@PathVariable String email);

}
