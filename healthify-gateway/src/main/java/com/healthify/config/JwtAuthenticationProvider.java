package com.healthify.config;

import com.healthify.dto.JwtAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationProvider implements ReactiveAuthenticationManager {

	@Autowired
	private TokenProvider tokenProvider;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Mono authenticate(Authentication authentication) {
		String token = (String) authentication.getCredentials();
		tokenProvider.validateToken(token);
		return Mono.just(new JwtAuthentication(tokenProvider.getSubject(token), token, tokenProvider.getAuthorities(token)));
	}
}