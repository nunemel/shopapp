package com.egs.shopapp.service;

import java.util.List;
import java.util.Optional;

import com.egs.shopapp.model.Category;
import com.egs.shopapp.model.dto.CategoryDto;

/**
 * Service for {@link Category} entity
 *
 * @author Nune Melikyan
 */
public interface CategoryService {
	
	/**
	 * 
	 * @param id The Category id.
	 * @return The Optional<Category> object.
	 */
	public Optional<Category> findById(Long id);

		
	 /**
     * Gets all products present in the system.
     * 
     * @return The set of categories.
     */
	List<Category> findAll();
	
	/**
	 * 
	 * @param categoryDto the Category DTO.
	 * @return The Category object.
	 */
	public Category save(CategoryDto categoryDto);
	
	/**
	 * Delete the Category object.
	 * 
	 * @param category The Category object.
	 * 
	 */
	void delete(Category category);

	/**
	 * Delete the Category object.
	 * @param category
	 * @param categoryDto The Ctegory DTO.
	 * @return The Category object.
	 */
	Category update(Category category, CategoryDto categoryDto);	
	
	/**
	 * 
	 * @param category
	 * @param parent
	 * @return
	 */
	boolean isChildCategory(Category category, Category parent);
	
	/**
	 * 
	 * @param category
	 * @param parent
	 */
	void addChildCategory(Category category, Category parent);
	
	/**
	 * 
	 * @param category
	 * @param parent
	 */
	void removeChildCategory(Category category, Category parent);
}
