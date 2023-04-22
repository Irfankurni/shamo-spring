package com.example.shamo.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.example.shamo.service.UserService;

@EnableWebSecurity
public class ApiSecurity {

	@Autowired
	private UserService userService;

	@Autowired
	private List<RequestMatcher> antMatchers;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorizationFilter authFilter;

	@Bean
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().addFilterAfter(authFilter, BasicAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer configure() throws Exception {
		return (web) -> antMatchers.forEach(matcher -> web.ignoring().requestMatchers(matcher));
	}

	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
