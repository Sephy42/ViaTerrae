package com.formation.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class NotApplicableException extends RuntimeException {

	private static final long serialVersionUID = -1749672979746392283L;
	
	public NotApplicableException() {
	}

	public NotApplicableException(String msg) {
		super(msg);
	}

}
