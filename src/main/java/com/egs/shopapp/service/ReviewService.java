package com.egs.shopapp.service;

import java.util.List;
import java.util.Optional;

import com.egs.shopapp.model.Product;
import com.egs.shopapp.model.Review;
import com.egs.shopapp.model.dto.ReviewDto;

/**
 * Service for {@link Review} entity.
 * 
 * @author Nune
 *
 */
public interface ReviewService {
	
	/**
	 * Find the Review object by id.
	 * @param id The review id.
	 * @return  The Optional<Review> object. 
	 */
	public Optional<Review> findById(Long id);

	/**
	 * 
	 * @param The product id.
	 * @return List of reviews by product id.
	 */
	List<Review> findByProductId(Long productId);
	
	/**
	 * Find the all paginated Review objects.
	 * 
	 * @param review The Review DTO object.
	 */
	List<Review> findAll();
	
	/**
	 * Find by Review objects by Rating range.
	 * @param since starting rating.
	 * @param until ending rating.
	 * @return The List<Review> object.
	 */
	List<Review> findByRatingRange(Integer since, Integer until);
	
	/**
	 * 
	 * @param review The Review DTO object.
	 * @return The Review object.
	 */
	public Review save(ReviewDto reviewDto);
	
	/**
	 * Update the Review object.
	 * @param review
	 * @param reviewDto
	 */
	public void update(Review review, ReviewDto reviewDto);
		
	/**
	 * Delete the Review object.
	 * 
	 * @param review The Review object.
	 * 
	 */
	void delete(Review review);
	
	/**
	 * Delete the Review by id.
	 * @param id
	 */
	void deleteById(Long id);

	/**
	 * Add the Review to Product.
	 */
	void addReviewToProduct(ReviewDto reviewDto, Product product);

	/**
	 * Remove Review from Product.
	 * @param review The Review object.
	 */
	void removeReviewFromProduct(Review review);

	/**
	 * Find Review objects by user id.
	 * @param userId The user id.
	 * @return The Optional<Review> object.
	 */

	/**
	 * Find Review objects by username.
	 * @param username The username.
	 * @return
	 */
	Optional<Review> findByUsername(String username);
	
	/**
	 * 
	 * @param comment
	 * @return
	 */
	List<Review> findByCommentContainingIgnoreCase(String comment);

	/**
	 * 
	 * @param productid
	 * @return
	 */
	public List<Review> findReviewsByProductId(Long productid);
}
