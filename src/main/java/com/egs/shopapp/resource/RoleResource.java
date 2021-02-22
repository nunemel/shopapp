package com.egs.shopapp.resource;

import org.springframework.hateoas.ResourceSupport;

public class RoleResource extends ResourceSupport {

	private final String name;
	private final String description;

	public RoleResource(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
