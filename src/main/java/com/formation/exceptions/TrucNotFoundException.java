package com.formation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.NOT_FOUND)
public class TrucNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TrucNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
