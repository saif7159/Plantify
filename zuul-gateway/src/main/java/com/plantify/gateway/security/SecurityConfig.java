package com.plantify.gateway.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/user/**").permitAll()
				.antMatchers(HttpMethod.POST, "/catalogue/**").hasRole("Admin")
				.antMatchers(HttpMethod.PUT, "/catalogue/**").hasRole("Admin").anyRequest().authenticated().and()
				.addFilterAfter(new JwtUsernameAndPasswordAuthorizationFilter(),
						UsernamePasswordAuthenticationFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

}
