package com.example.demo.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JWTFilterInvalidException extends AuthenticationException {

	public JWTFilterInvalidException(String msg) {
		super(msg);
	}
	
}
