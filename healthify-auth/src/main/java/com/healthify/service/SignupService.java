package com.healthify.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthify.config.JwtService;
import com.healthify.config.UserPrinciple;
import com.healthify.dto.JwtResponseDto;
import com.healthify.dto.LoginDto;
import com.healthify.dto.SignUpDto;
import com.healthify.entity.Role;
import com.healthify.entity.RoleName;
import com.healthify.entity.User;
import com.healthify.repository.RoleRepository;
import com.healthify.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
	AuthenticationProvider authenticationProvider;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
	PasswordEncoder encoder;
    @Autowired
	JwtService jwtService;

    public ResponseEntity<JwtResponseDto> signin(LoginDto loginRequest) {
        try{
Authentication authentication = authenticationProvider.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserPrinciple userObj = (UserPrinciple) authentication.getPrincipal();
		String roles = userObj.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(", "));
		Map<String, Object> claim = new HashMap<>();
		claim.put("id", userObj.getId());
		claim.put("username", userObj.getUsername());
		claim.put("role", roles);
		String jwt = jwtService.generateToken(claim, userObj);
		return ResponseEntity.ok(new JwtResponseDto(jwt));
        }catch (Exception e){
            log.error("Error Occurred while signin for username={}",loginRequest.getUsername(), e);
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public ResponseEntity<String> saveUser(SignUpDto signUpRequest) {
        try{
            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<String>("Fail -> Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
				case "superdmin":
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
		user.setCredentialsNonExpired(true);
        user.setSignupDate(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setAccountStatus(true);
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());
        user.setAccountStatus(true);
		userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
        }catch (Exception e){
            log.error("Error Occurred while saving user for username={}",signUpRequest.getUsername(), e);
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
