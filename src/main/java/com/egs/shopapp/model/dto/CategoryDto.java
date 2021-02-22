package com.egs.shopapp.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.egs.shopapp.model.Category;

/**
 * Category DTO.
 * 
 * @author Nune
 *
 */
@Validated
public class CategoryDto {
	
	@NotEmpty(message = "username is empty")
	@NotNull(message = "username is null")
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	private String description;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
     * 
     * @return Category from DTO.
     */
	public Category getCreateCategoryFromDto(){
    	
		Category category = new Category();
        
		category.setName(name);
		category.setDescription(description);    
        
        return category;
    }
	
	/**
     * 
     * @return Category from DTO.
     */
	public Category getUpdateCategoryFromDto(Category category){
    	
		category.setName(name);
		category.setDescription(description);    
        
        return category;
    }
}
