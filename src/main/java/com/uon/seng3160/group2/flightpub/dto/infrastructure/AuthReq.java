package com.uon.seng3160.group2.flightpub.dto.infrastructure;

public class AuthReq {
	
	private String username;
	private String password;
	
	public AuthReq() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
