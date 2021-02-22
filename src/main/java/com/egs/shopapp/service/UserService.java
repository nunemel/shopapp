package com.egs.shopapp.service;

import java.util.List;
import java.util.Optional;

import com.egs.shopapp.model.Address;
import com.egs.shopapp.model.User;
import com.egs.shopapp.model.dto.AddressDto;
import com.egs.shopapp.model.dto.UserDto;

/**
 * Service for {@link User} entity.
 * 
 * @author Nune
 *
 */
public interface UserService {
	
	/**
	 * Creates the User object.
	 * @param user The user DTO.
	 * @return
	 */
	User save(UserDto userDto);
	
	/**
	 * Return the current User.
	 * @return The current User.
	 */
	User getCurrentUser();
	
	/**
	 * Find User by username.
	 * @param username The username.
	 * @return The Optional<User> object.
	 */
	Optional<User> findByUsername(String username);
	
	/**
	 * Find User by id.
	 * @param id  The User id.
	 * @return The User object.
	 */
	Optional<User> findById(Long id);
	
	/**
	 * Find User by email.
	 * @param email The User email.
	 * @return Optional<User> object.
	 */
	Optional<User> findByEmail(String email);
	  
    /**
     * 
     * @param pageable The pageable objec.
     * @return The List of User object.
     */
	List<User> findAll();
    
	/**
	 * 
	 * @param isActive If true activate the User, false deactivate the User.
	 * @param id The User id.
	 */
    void deactivateActivateUser(Boolean isActive, Long id);
        
    /**
     * Delete User by id.
     * @param id The User id.
     */
    void deleteById(Long id);

    /**
     * 
     * @param user The User object.
     * @param userDto The User DTO.
     */
	void update(User user, UserDto userDto);

	/**
	 * 
	 * @param user The User object.
	 * @param address The Address object.
	 */
	void removeUserAddress(User user, Address address);
	
	/**
	 * 
	 * @param user The User object.
	 * @param addressDto The Address DTO.
	 */
	void addUserAddress(User user, AddressDto addressDto);
}
