package com.formation.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotAuthorizedException() {
	}

	public NotAuthorizedException(String msg) {
		super(msg);
	}

}
