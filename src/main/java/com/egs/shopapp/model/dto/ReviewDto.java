package com.egs.shopapp.model.dto;

import javax.validation.constraints.Digits;

import org.springframework.validation.annotation.Validated;

import com.egs.shopapp.model.Review;

/**
 * Review DTO.
 * 
 * @author Nune
 *
 */
@Validated
public class ReviewDto {
	
	@Digits(integer = 1, fraction = 0)
	private Integer rating;

	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	private String comment;
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
     * 
     * @return Review from DTO.
     */
	public Review getCreateReviewFromDto(){
    	
		Review review = new Review();
        
        review.setRating(rating);
        review.setComment(comment);
        
        return review;
    }
	
	/**
     * 
     * @return Review from DTO.
     */
	public Review getUpdateReviewFromDto(Review review){
     
        review.setRating(rating);
        review.setComment(comment);
        
        return review;
    }
}
