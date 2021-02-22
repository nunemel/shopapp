package com.egs.shopapp.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.egs.shopapp.model.Role;
import com.egs.shopapp.validator.RoleSubSet;

/**
 * Role DTO.
 * 
 * @author Nune
 *
 */
@Validated
public class RoleDto {
	
	@RoleSubSet(anyOf = {"USER", "ADMIN"})
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
     * @return The Role object from DTO.
     */
	public Role getCreateRoleFromDto(){
    	
        Role role = new Role();
        
        role.setName(name);
        role.setDescription(description);    
        
        return role;
    }
	
	/**
     * 
     * @return The Role object from DTO.
     */
	public Role getUpdateRoleFromDto(Role role){
    	
        role.setName(name);
        role.setDescription(description);    
        
        return role;
    }
}
