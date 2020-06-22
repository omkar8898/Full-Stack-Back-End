package com.fullstack.ecommerce.dto;

public class JwtRequestDTO {
	
	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtRequestDTO [username=" + username + ", password=" + password + "]";
	}
	
}
