package com.formation.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ViaTerraeException extends RuntimeException {

	public static final String RG_23456 = "RG-23456";
	public static final String RG_MAIL_NOT_UNIQUE = "RG-12345";
	
	private static final long serialVersionUID = -1749672979746392283L;


	public ViaTerraeException(String msg) {
		super(msg);
	}

}
