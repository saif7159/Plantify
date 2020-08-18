package com.plantify.user.service;

import java.util.Optional;

import com.plantify.user.model.Login;

public interface UserService {
	public Login createUser(Login login);

	public Optional<Login> getUser(int id);

	public Login findByEmail(String email);
}
