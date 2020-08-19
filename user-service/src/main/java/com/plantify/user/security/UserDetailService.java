package com.plantify.user.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.plantify.user.model.Login;
import com.plantify.user.service.UserService;

@Service
public class UserDetailService implements UserDetailsService {
	@Autowired
	private UserService service;

	private static final Logger log = LoggerFactory.getLogger(UserDetailService.class);

	@Override
	// Im treating the email as username itself here.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Check User: " + username);
		Login login = service.findByEmail(username);
		log.info("Login Details: " + login.getEmail() + login.getPassword());
		List<GrantedAuthority> grantedAuths = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_" + login.getRole());
		return new User(login.getEmail(), login.getPassword(), grantedAuths);
	}

}
