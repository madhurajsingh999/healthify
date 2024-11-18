package com.healthify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthentication {
	private String subject;
	private String token;
	private String authorities;

	public JwtAuthentication(String subject, String token, String authorities) {
		this.subject = subject;
		this.token = token;
		this.authorities = authorities;
	}
}