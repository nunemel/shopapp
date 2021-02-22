package com.egs.shopapp.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.egs.shopapp.model.User;

/**
 * User DTO object.
 * 
 * @author Nune
 *
 */
@Validated
public class UserDto {
	
	@NotEmpty(message = "username is empty")
	@NotNull(message = "username is null")
	private String username;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @NotEmpty(message = "password is empty")
	@NotNull(message = "password is null")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    @NotEmpty(message = "email is empty")
    @NotNull(message = "email is null")
    @Email(message = "invalid email format")
    private String email;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
      
    @NotEmpty(message = "first name is empty")
	@NotNull(message = "first name is null")
    private String firstName;
    
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@NotEmpty(message = "last name is empty")
	@NotNull(message = "last name is null")
	private String lastName;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
		
    /**
     * 
     * @return User object from DTO object.
     */
	public User getCreateUserFromDto(){
    	
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
                
        return user;
    }
	
	/**
     * 
     * @param user The User object.
     * @return User object from DTO object.
     */
	public User getUpdateUserFromDto(User user){
    	       
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
                
        return user;
    }  
}