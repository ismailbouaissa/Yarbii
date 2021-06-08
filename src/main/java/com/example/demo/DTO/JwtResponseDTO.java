package com.example.demo.DTO;


public class JwtResponseDTO {
    private String token;

	public JwtResponseDTO(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
