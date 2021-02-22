package com.egs.shopapp.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.egs.shopapp.dao.ReviewDao;
import com.egs.shopapp.model.Product;
import com.egs.shopapp.model.Review;
import com.egs.shopapp.model.dto.ReviewDto;
import com.egs.shopapp.service.ReviewService;

@Service(value = "reviewService")
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	@Transactional
	public List<Review> findAll() {
		return reviewDao.findAll();
	}

	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public Optional<Review> findById(Long id) {
		return reviewDao.findById(id);
	}

	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public List<Review> findByProductId(Long productId) {
		return reviewDao.findByProduct_Id(productId);
	}
	
	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public List<Review> findByRatingRange(Integer since, Integer until) {
		return reviewDao.findByRatingBetween(since, until);
	}

	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	@Transactional
	public Review save(ReviewDto reviewDto) {		
		return reviewDao.save(reviewDto.getCreateReviewFromDto());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public void update(Review review, ReviewDto reviewDto) {
		reviewDao.save(reviewDto.getUpdateReviewFromDto(review));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public void delete(Review review) {
		reviewDao.delete(review);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public void deleteById(Long id) {
		reviewDao.deleteById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
    public void addReviewToProduct(ReviewDto reviewDto, Product product) {
        Review review = reviewDto.getCreateReviewFromDto();		
		review.setProduct(product);
        reviewDao.save(review);
    }

    @PreAuthorize("hasRole('ADMIN')")
	@Transactional
    @Override
    public void removeReviewFromProduct(Review review) {
        review.setProduct(null);
        reviewDao.save(review);
    }

	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public Optional<Review> findByUsername(String username) {
		return reviewDao.findByCreatedBy(username);
	}

	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public List<Review> findByCommentContainingIgnoreCase(String comment) {	
		return reviewDao.findByCommentContainingIgnoreCase(comment);
	}

	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public List<Review> findReviewsByProductId(Long productid) {
		return reviewDao.findByProduct_Id(productid);
	}
}
