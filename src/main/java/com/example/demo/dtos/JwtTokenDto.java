package com.example.demo.dtos;

public class JwtTokenDto {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public JwtTokenDto(String token) {
		super();
		this.token = token;
	}
	
}
