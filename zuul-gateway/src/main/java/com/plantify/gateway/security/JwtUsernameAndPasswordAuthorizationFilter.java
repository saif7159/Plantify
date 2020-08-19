package com.plantify.gateway.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUsernameAndPasswordAuthorizationFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(JwtUsernameAndPasswordAuthorizationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader(SecurityConstants.HEADER_STRING);

		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = header.replace(SecurityConstants.TOKEN_PREFIX, "");
		log.info("Token zuul:" + token);

		try {

			Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
			String username = claims.getSubject();
			log.info("The authorized Username:" + username);
			if (username != null) {
				@SuppressWarnings("unchecked")
				List<String> authorities = (List<String>) claims.get("authorities");
				log.info("Authority" + authorities);

				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
						authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

				// Now, user is authenticated
				System.out.println(auth.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

		} catch (Exception e) {
			e.printStackTrace();
			SecurityContextHolder.clearContext();
		}

		filterChain.doFilter(request, response);
	}

}
