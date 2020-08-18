package com.plantify.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plantify.user.dao.UserDetailsDao;
import com.plantify.user.model.Login;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDetailsDao userdao;

	@Override
	public Login createUser(Login login) {
		return userdao.save(login);

	}

	@Override
	public Optional<Login> getUser(int id) {
		return userdao.findById(id);
	}

	@Override
	public Login findByEmail(String email) {
		return userdao.findByEmail(email);
	}

}
