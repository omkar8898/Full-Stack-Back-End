package com.fullstack.ecommerce.dto;

import java.util.List;

public class JwtResponseDTO {
	
	private String token;
	
	private Boolean success;
	
	private String username;
	
	private String errorMessage;
	
	private List<String> roles;
	

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public JwtResponseDTO(String token, Boolean success, String username,
			String errorMessage, List<String> roles) {
		super();
		this.token = token;
		this.success = success;
		this.username = username;
		this.errorMessage = errorMessage;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "JwtResponseDTO [token=" + token + ", success=" + success + ", username=" + username + ", errorMessage="
				+ errorMessage + ", roles=" + roles + "]";
	}

	

}
