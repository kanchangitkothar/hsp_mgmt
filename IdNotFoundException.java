package com.jsp.hsp_mgmt_systm.exception;

public class IdNotFoundException extends RuntimeException{
	
	private String message = "id not found";
	
	@Override
	public String getMessage() {
		return message;
	}

	public IdNotFoundException(String message) {
		super();
		this.message = message;
	}

	public IdNotFoundException() {
		super();
	}
}
