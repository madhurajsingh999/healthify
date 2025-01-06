package com.healthify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String roles;
	private String token;
	private String type;
	private String error;
}