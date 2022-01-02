package com.naveen.userservice.model;

public class AuthenticationResponse {
	
	private final String jwt;
	private int userId;
	private String name;
	
	public AuthenticationResponse(String jwt, int userId, String name) {
		super();
		this.jwt = jwt;
		this.userId = userId;
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJwt() {
		return jwt;
	}
	@Override
	public String toString() {
		return "AuthenticationResponse [jwt=" + jwt + ", userId=" + userId + ", name=" + name + "]";
	}	
	
}
