package com.egs.shopapp.service;

import java.util.List;
import java.util.Optional;

import com.egs.shopapp.model.Image;
import com.egs.shopapp.model.dto.ImageDto;

/**
 * Service for {@link Image} entity
 *
 * @author Nune Melikyan
 */
public interface ImageService {
	
	/**
	 * 
	 * @param id The Image id.
	 * @return The Optional<Image> object.
	 */
	 Optional<Image> findById(Long id);

	/**
	 * 
	 * @param The product id.
	 */
	List<Image> findByProductId(Long productId);
	
	/**
	 * Find the all paginated Image objects.
	 * 
	 * @param imageDto The Image DTO object.
	 */
	List<Image> findAll();
	
	/**
	 * 
	 * @param imageDto the Image DTO object.
	 * @return The Image object.
	 */
	Image save(ImageDto imageDto);
		
	/**
	 * Delete the Image object.
	 * 
	 * @param image The Image object.
	 * 
	 */
	void delete(Image image);

	/**
	 * 
	 * @param image
	 * @param imageDto
	 */
	 void update(Image image, ImageDto imageDto);
}