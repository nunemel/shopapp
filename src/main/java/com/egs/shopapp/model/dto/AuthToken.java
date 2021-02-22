package com.egs.shopapp.model.dto;

/**
 * AuthToken object for token.
 * 
 * @author Nune
 *
 */
public class AuthToken {

	public AuthToken() {
	}

	public AuthToken(String token) {
		this.token = token;
	}

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}