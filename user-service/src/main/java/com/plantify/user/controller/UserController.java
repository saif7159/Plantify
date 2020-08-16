package com.plantify.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public void createUser(@RequestBody Login login) {
		login.setPassword(encoder.encode(login.getPassword()));
		userService.createUser(login);
	}

	@GetMapping("/getUser/id/{id}")
	public Login getUser(@PathVariable int id) {
		return userService.getUser(id).get();
	}

	@GetMapping("/getUser/email/{email}")
	public Login getUserByEmail(@PathVariable String email) {
		return userService.findByEmail(email);
	}
}