package com.healthify.controller;

import com.healthify.dto.JwtResponseDto;
import com.healthify.dto.LoginDto;
import com.healthify.dto.SignUpDto;
import com.healthify.service.SignupService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*")
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
}