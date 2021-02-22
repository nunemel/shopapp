package com.egs.shopapp.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

/**
 * LoginUser object for login.
 * 
 * @author Nune
 *
 */
@Validated
public class LoginDto {

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
}