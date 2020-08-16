package com.plantify.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plantify.user.model.Login;
@Repository
public interface UserDetailsDao extends JpaRepository<Login, Integer>{
	public Login findByEmail(String email);
}
