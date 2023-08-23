package com.uon.seng3160.group2.flightpub.dto.infrastructure;

public class AuthRes {
	
	private final String jwtToken;

	public AuthRes(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

}
