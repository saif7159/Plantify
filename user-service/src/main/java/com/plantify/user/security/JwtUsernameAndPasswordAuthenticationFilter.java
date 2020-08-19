package com.plantify.user.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plantify.user.model.UserCredential;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	AuthenticationManager authenticationManager;

	private static final Logger log = LoggerFactory.getLogger(JwtUsernameAndPasswordAuthenticationFilter.class);

	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {

			UserCredential login = new ObjectMapper().readValue(request.getInputStream(), UserCredential.class);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getUsername(),
					login.getPassword(), Collections.emptyList());

			// Authentication manager authenticate the user, and use
			// UserDetialsServiceImpl::loadUserByUsername() method to load the user.
			return authenticationManager.authenticate(authToken);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		log.info("User Authenticated Successfully");
		Long now = System.currentTimeMillis();
		log.info("Granted Authorities: "
				+ auth.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList()).toString());
		String token = Jwts.builder().setSubject(auth.getName())
				.claim("authorities",
						auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(now)).setExpiration(new Date(now + SecurityConstants.EXPIRATION_TIME)) // milliseconds
				.signWith(SignatureAlgorithm.HS512, "secret").compact();
		log.info("Generated Token" + token);
		// Adding token to header
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
	}

}
