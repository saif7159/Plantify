package com.plantify.cart.client;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.plantify.cart.model.Login;

@Component
public class UserClientFallback implements UserClient{

	@Override
	public Login getUserByEmail(String email) {
		return new Login();
	}

}
