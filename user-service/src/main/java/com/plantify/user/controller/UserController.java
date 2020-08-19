package com.plantify.user.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plantify.user.exception.UserNotFoundException;
import com.plantify.user.model.Login;
import com.plantify.user.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@PostMapping("/create-user")
	public Login createUser(@RequestBody Login login) {
		login.setPassword(encoder.encode(login.getPassword()));
		return userService.createUser(login);
	}

	@GetMapping("/getUser/id/{id}")
	public Login getUser(@PathVariable int id) {
		Optional<Login> login = userService.getUser(id);
		if (login.isEmpty())
			throw new UserNotFoundException("User with id " + id + " is not found");
		return userService.getUser(id).get();
	}

	@GetMapping("/getUser/email/{email}")
	public Login getUserByEmail(@PathVariable String email) {
		Login login = userService.findByEmail(email);
		if (login == null)
			throw new UserNotFoundException("User with email " + email + " is not found");
		return login;
	}
}
