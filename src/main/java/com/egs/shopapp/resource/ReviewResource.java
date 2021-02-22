package com.egs.shopapp.resource;

import org.springframework.hateoas.ResourceSupport;

/**
 * Spring HATEOAS-oriented DTO for {@see Review} entity
 * 
 * @author Nune Melikyan
 *
 */
public class ReviewResource extends ResourceSupport {
	 	
	private final Integer rating; 
	private final String comment; 
	private final Long productId; 
	private final String owner;
	
	public ReviewResource(Integer rating, String comment, Long productId, String owner) {
		this.rating = rating;
		this.comment = comment;
		this.productId = productId;
		this.owner = owner;
	}

	public Integer getRating() {
		return rating;
	}

	public String getComment() {
		return comment;
	}

	public Long getProductId() {
		return productId;
	}

	public String getOwner() {
		return owner;
	} 
}
