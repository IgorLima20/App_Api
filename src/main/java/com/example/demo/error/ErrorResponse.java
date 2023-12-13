package com.example.demo.error;

public class ErrorResponse {

    private final int status;
    
    private final String error;

	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public ErrorResponse(int status, String error) {
		super();
		this.status = status;
		this.error = error;
	}
	
}
