package com.fullstack.ecommerce.dto;

public class ResponseDTO {
	
	private boolean success;
	
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public ResponseDTO(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseDTO [success=" + success + ", message=" + message + "]";
	}
	
}
