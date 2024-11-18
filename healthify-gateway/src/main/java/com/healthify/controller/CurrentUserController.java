package com.healthify.controller;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CurrentUserController {

	@GetMapping("/me")
	public Mono<Map<String, Object>> current(@AuthenticationPrincipal Mono<UserDetails> principal) {
		return principal.map(user -> Map.of("name", user.getUsername(), "roles",
				AuthorityUtils.authorityListToSet(user.getAuthorities())));
	}

}