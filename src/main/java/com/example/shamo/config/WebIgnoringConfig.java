package com.example.shamo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class WebIgnoringConfig {

	@Bean
	public List<RequestMatcher> antMatchers() {
		List<RequestMatcher> matchers = new ArrayList<RequestMatcher>();
		matchers.add(new AntPathRequestMatcher("/files/**", HttpMethod.GET.name()));

		return matchers;
	}

}
