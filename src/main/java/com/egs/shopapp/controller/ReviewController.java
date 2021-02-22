package com.egs.shopapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egs.shopapp.assembler.ReviewResourceAssembler;
import com.egs.shopapp.model.Review;
import com.egs.shopapp.model.User;
import com.egs.shopapp.model.dto.ReviewDto;
import com.egs.shopapp.service.ReviewService;
import com.egs.shopapp.service.UserService;

/**
 * Endpoint for review management.
 *
 * @author Nune 
 */
@RestController
@RequestMapping(path = "/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private ReviewResourceAssembler reviewResourceAssembler;
    
    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> retrieveAllReviews() {
	
		final List<Review> reviews = reviewService.findAll();

		return ResponseEntity.ok(reviewResourceAssembler.toResources(reviews));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveReview(@PathVariable Long id) {
	
		final Optional<Review> review = reviewService.findById(id);

        if (review.isEmpty()) {
        	return ResponseEntity.ok("No review found by id:" + id);
        }
        
		return ResponseEntity.ok(reviewResourceAssembler.toResource(review.get()));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createReview(@RequestBody @Valid ReviewDto reviewDto) {
		Review review = reviewService.save(reviewDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewResourceAssembler.toResource(review));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateReview(@PathVariable Long id, @RequestBody @Valid ReviewDto reviewDto) {
	
		final Optional<Review> review = reviewService.findById(id);

        if (review.isEmpty()) {
        	return ResponseEntity.ok("No review found by id:" + id);
        }

		reviewService.update(review.get(), reviewDto);

		return ResponseEntity.ok("Review " + id + " successfully updated.");
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteReview(@PathVariable Long id) {
		
		final Optional<Review> review = reviewService.findById(id);

        if (review.isEmpty()) {
        	return ResponseEntity.ok("No review found by id:" + id);
        }

		reviewService.delete(review.get());

		return ResponseEntity.ok("Review " + id + " successfully deleted.");
	}
	
	 @RequestMapping(path = "/", method = RequestMethod.GET)
	    public ResponseEntity<?> getUserReviews(@RequestParam Long userid) {
		 
		 final Optional<User> user = userService.findById(userid);

	        if (user.isEmpty()) {
	        	return ResponseEntity.ok("No user found by userid:" + userid);
	        }
	        
	        Optional<Review> review = reviewService.findByUsername(user.get().getUsername());
	        
	        if (review.isEmpty()) {
	        	return ResponseEntity.ok("No review found by username:" + user.get().getUsername());
	        }
	        
	        return ResponseEntity.ok(reviewResourceAssembler.toResource(review.get()));
	  }
	 
		@RequestMapping(path = "/search", method = RequestMethod.GET)
		public ResponseEntity<?> findReviewsByRatingRange(
				@RequestParam("rating_since") @Min(1) @Max(5) Integer ratingSince,
				@RequestParam("rating_until") @Min(1) @Max(5) Integer ratingUntil) {

			List<Review> reviews = reviewService.findByRatingRange(ratingSince, ratingUntil);

			if (reviews == null || reviews.isEmpty()) {
				return ResponseEntity.ok("No reviews found.");
			}

			return ResponseEntity.ok(reviewResourceAssembler.toResources(reviews));
		}
	 
}
