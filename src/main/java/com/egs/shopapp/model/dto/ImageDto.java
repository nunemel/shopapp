package com.egs.shopapp.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.egs.shopapp.model.Image;

/**
 * Image DTO.
 * 
 * @author Nune
 *
 */
@Validated
public class ImageDto {
	
	@NotEmpty(message = "name is empty")
	@NotNull(message = "name is null")
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty(message = "path is empty")
	@NotNull(message = "path is null")
	private String path;
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
     * @return Image from DTO.
     */
	public Image getCreateImageFromDto(){
    	
		Image image = new Image();
        
        image.setName(name);
        image.setDescription(path);
        image.setDescription(description);
                
        return image;
    }
	
	/**
     * 
     * @return Image from DTO.
     */
	public Image getUpdateImageFromDto(Image image){
    	
        image.setName(name);
        image.setDescription(path);
        image.setDescription(description);
                
        return image;
    }
}
