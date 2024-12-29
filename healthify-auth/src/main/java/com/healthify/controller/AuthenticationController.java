package com.healthify.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import com.healthify.config.JwtProvider;
import com.healthify.config.JwtService;
import com.healthify.config.UserPrinciple;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "*")
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
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<String>("Fail -> Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
				case "superadmin":
					Role adminRole = roleRepository.findByName(RoleName.ROLE_SUPER_ADMIN)
							.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
					roles.add(adminRole);

					break;
				case "client":
					Role clientRole = roleRepository.findByName(RoleName.ROLE_CLIENT)
							.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
					roles.add(clientRole);

					break;
				case "admin":
					Role pmRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
					roles.add(pmRole);

					break;
				default:
					Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
					roles.add(userRole);
			}
		});
		user.setRoles(roles);
		user.setTwoFactorEnabled(true);
		user.setSignupDate(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		user.setCredentialsNonExpired(true);
		user.setAccountStatus(true);
		user.setCreatedDate(LocalDateTime.now());
		user.setUpdatedDate(LocalDateTime.now());
		user.setEmailVerified(true);
		user.setFailedLoginAttempts(0);
		userRepository.save(user);

		return ResponseEntity.ok().body("User registered successfully!");
	}
}