package com.example.shamo.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.shamo.dto.ErrorRes;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtComponent jwtComponent;

	@Autowired
	private List<RequestMatcher> antMatchers;

	@Autowired
	private ObjectMapper objMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		long count = antMatchers.stream().filter(matcher -> matcher.matches(request)).collect(Collectors.counting());
		if (count == 0 && !request.getRequestURI().equals("/login")) {
			String authorization = request.getHeader("Authorization");
			String[] splitAuth = authorization.split(" ");
			String token = splitAuth[1];

			try {
				String id = jwtComponent.getClaimId(token);
				Authentication auth = new UsernamePasswordAuthenticationToken(id, null);
				SecurityContextHolder.getContext().setAuthentication(auth);
			} catch (Exception e) {
				response.setStatus(401);
				ErrorRes<String> errorRes = new ErrorRes<String>();
				errorRes.setMessage("Invalid token!");
				response.getWriter().append(objMapper.writeValueAsString(errorRes));
				response.setContentType("application/json");
				e.printStackTrace();
				return;
			}
		}

		filterChain.doFilter(request, response);
	}

}
