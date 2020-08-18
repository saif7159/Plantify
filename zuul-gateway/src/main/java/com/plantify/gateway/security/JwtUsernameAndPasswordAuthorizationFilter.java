package com.plantify.gateway.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUsernameAndPasswordAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader(SecurityConstants.HEADER_STRING);

		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = header.replace(SecurityConstants.TOKEN_PREFIX, "");
		System.out.println("Token zuul:"+token);

		try { // exceptions might be thrown in creating the claims if for example the token is
				// expired

			// 4. Validate the token
			System.out.println("Hi");
			Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token)
					.getBody();
			String username = claims.getSubject();
			System.out.println("The authorize username:"+username);
			if (username != null) {
				@SuppressWarnings("unchecked")
				List<String> authorities = (List<String>) claims.get("authorities");
				System.out.println("authority"+authorities);
				// 5. Create auth object
				// UsernamePasswordAuthenticationToken: A built-in object, used by spring to
				// represent the current authenticated / being authenticated user.
				// It needs a list of authorities, which has type of GrantedAuthority interface,
				// where SimpleGrantedAuthority is an implementation of that interface
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
						authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

				// 6. Authenticate the user
				// Now, user is authenticated
				System.out.println(auth.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

		} catch (Exception e) {
			// In case of failure. Make sure it's clear; so guarantee user won't be
			// authenticated
			e.printStackTrace();
			SecurityContextHolder.clearContext();
		}

		// go to the next filter in the filter chain
		filterChain.doFilter(request, response);
	}

}
