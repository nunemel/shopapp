package com.egs.shopapp.resource;

import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {

	private final String username;
	private final String password;
	private final String email;
	private final String firstName;
	private final String lastName;
	
	public UserResource(String username, 
			String password, 
			String firstName, 
			String lastName,
			String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}
