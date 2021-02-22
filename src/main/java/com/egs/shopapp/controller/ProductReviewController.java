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
import com.egs.shopapp.model.Product;
import com.egs.shopapp.model.Review;
import com.egs.shopapp.model.dto.ReviewDto;
import com.egs.shopapp.service.ProductService;
import com.egs.shopapp.service.ReviewService;

@RestController
@RequestMapping(path = "/products/{productid}/reviews")
public class ProductReviewController {
	
	@Autowired
    private ReviewService reviewService;
		
	@Autowired
    private ProductService productService;
	
	@Autowired
    private ReviewResourceAssembler reviewResourceAssembler;
	
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createProductReview(@PathVariable Long productid, 
    		@RequestBody @Valid ReviewDto reviewDto) {
    	
    	final Optional<Product> product = productService.findById(productid);

        if (product.isEmpty()) {
        	return ResponseEntity.ok("No product found by id:" + productid);
        }
        
        reviewService.addReviewToProduct(reviewDto, product.get());
        
        return ResponseEntity.status(HttpStatus.CREATED)
        		.body("Review successfully added to product.");
    }
   
    @RequestMapping(path = "/{reviewid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductFromReview(@PathVariable Long productid, 
    		@PathVariable Long reviewid) {
    	
    	final Optional<Product> product = productService.findById(productid);

        if (product.isEmpty()) {
        	return ResponseEntity.ok("No product found by id:" + productid);
        }
        
        final Optional<Review> review = reviewService.findById(reviewid);

        if (review.isEmpty()) {
        	return ResponseEntity.ok("No review found by reviewid:" + reviewid);
        } 
        
        reviewService.removeReviewFromProduct(review.get());
        
        return ResponseEntity.ok("Review successfully removed from product:" + productid);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getReviewsByProductId(@PathVariable Long productid) {
    	
    	final Optional<Product> product = productService.findById(productid);

        if (product.isEmpty()) {
        	return ResponseEntity.ok("No product found by id:" + productid);
        }
                
        List<Review> reviews = reviewService.findReviewsByProductId(productid);
        
        return ResponseEntity.ok(reviewResourceAssembler.toResources(reviews));
    }
    
    @RequestMapping(path = "/search", method = RequestMethod.GET)
	public ResponseEntity<?> findProductsByRatingRange(
			@RequestParam("rating_since") @Min(1) @Max(5) Integer ratingSince,
			@RequestParam("rating_until") @Min(1) @Max(5) Integer ratingUntil) {

		List<Review> reviews = reviewService.findByRatingRange(ratingSince, ratingUntil);

		if (reviews == null || reviews.isEmpty()) {
			return ResponseEntity.ok("No reviews found.");
		}

		return ResponseEntity.ok(reviewResourceAssembler.toResources(reviews));
	}

}
