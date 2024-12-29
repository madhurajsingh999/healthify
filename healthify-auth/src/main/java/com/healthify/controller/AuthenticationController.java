package com.healthify.controller;

import com.healthify.dto.JwtResponseDto;
import com.healthify.dto.LoginDto;
import com.healthify.dto.SignUpDto;
import com.healthify.service.SignupService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
public class AuthenticationController {

	@Autowired
	private SignupService signupService;

	@PostMapping("/signin")
	public ResponseEntity<JwtResponseDto> authenticateUser(@Valid @RequestBody LoginDto loginRequest) {
		return signupService.signin(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpDto signUpRequest) {
		return signupService.saveUser(signUpRequest);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout() {
		log.info("Logout Successful");
		return ResponseEntity.ok("Logout Successful");
	}

	@GetMapping("/me")
	public ResponseEntity<Object> me() {
		Object secObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(secObj);
	}
}