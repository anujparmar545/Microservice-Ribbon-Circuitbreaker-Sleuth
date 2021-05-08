package com.anuj.microservice1;

public class ApiError {

	
	private String exception;
	private int status;

	
	public ApiError() {}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
