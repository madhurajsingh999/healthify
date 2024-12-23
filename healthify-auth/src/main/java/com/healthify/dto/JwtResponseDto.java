package com.healthify.dto;

public class JwtResponseDto {
	private String token;
	private String type = "Bearer";
	private String error;

	public JwtResponseDto() {
	}

	public JwtResponseDto(String error, String accessToken) {
		this.error = error;
		this.token = accessToken;
	}

	public JwtResponseDto(String accessToken) {
		this.token = accessToken;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}