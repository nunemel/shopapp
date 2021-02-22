package com.egs.shopapp.resource;

import org.springframework.hateoas.ResourceSupport;

public class ImageResource extends ResourceSupport {

	private final String name;
	private final String path;
	private final String description;
	
	public ImageResource(String name, String path, String description) {
		super();
		this.name = name;
		this.path = path;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public String getDescription() {
		return description;
	}

}