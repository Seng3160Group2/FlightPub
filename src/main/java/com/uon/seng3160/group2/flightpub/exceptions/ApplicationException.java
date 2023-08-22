package com.hossein.FlightBooking.exceptions;

public class ApplicationException extends RuntimeException {

	public ApplicationException() {
		super();
	}
	
	public ApplicationException(String message) {
		super(message);
	}
	
}
