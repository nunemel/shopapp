package com.egs.shopapp.resource;

import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;
import java.util.List;

/**
 * Spring HATEOAS-oriented DTO for {@see Product} entity
 *
 * @author Nune Melikyan
 */
public class ProductResource extends ResourceSupport {
	
    private final String name;  
    private final BigDecimal price; 
    private final List<CategoryResource> categories; 
    private final String owner; 
    private final String description; 
    private final Integer qauntity;

   
	public ProductResource(String name, BigDecimal price, List<CategoryResource> categories,
			String owner, String description, Integer qauntity) {
		this.name = name;
		this.price = price;
		this.categories = categories;
		this.owner = owner;
		this.description = description;
		this.qauntity = qauntity;
	}


	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}


	public List<CategoryResource> getCategories() {
		return categories;
	}


	public String getOwner() {
		return owner;
	}

	public String getDescription() {
		return description;
	}

	public Integer getQauntity() {
		return qauntity;
	}
}
