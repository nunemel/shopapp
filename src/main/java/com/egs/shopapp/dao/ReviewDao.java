package com.egs.shopapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egs.shopapp.model.Review;

@Repository
public interface ReviewDao extends JpaRepository<Review, Long> {
	
  List<Review> findByRating(Integer rating);
  
  List<Review> findByRatingBetween(Integer since, Integer until);
  
  List<Review> findByProduct_Id(Long productId);
  
  List<Review> findByCommentContainingIgnoreCase(String comment); 
 
  Optional<Review> findByCreatedBy(String username);

}
