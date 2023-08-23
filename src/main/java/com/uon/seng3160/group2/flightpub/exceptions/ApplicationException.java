package com.uon.seng3160.group2.flightpub.exceptions;

public class ApplicationException extends RuntimeException {

	public ApplicationException() {
		super();
	}
	
	public ApplicationException(String message) {
		super(message);
	}
	
}
