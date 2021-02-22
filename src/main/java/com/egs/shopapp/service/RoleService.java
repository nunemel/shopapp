package com.egs.shopapp.service;

import java.util.List;
import java.util.Optional;

import com.egs.shopapp.model.Role;
import com.egs.shopapp.model.User;
import com.egs.shopapp.model.dto.RoleDto;

/**
 * Service for {@link Role} entity.
 * 
 * @author Nune
 */
public interface RoleService {
	
	/**
	 * @param name The role name.
	 * @return Role object by id.
	 */
	Optional<Role> findByName(String name);
    
    /**
     * @param userId The id of the user.
     * @return Role object by userId.
     */
	Optional<Role> findByUserId(Long userId);
     
    /**
     * Finds all roles of the system.
     * 
     * @return The list of requested roles.
     */
    List<Role> findAll();
    
    /**
	 * 
	 * @param  role The Role DTO object.
	 * @return The newly created Role.
	 */
    Role save(RoleDto role);
    
 
    /**
     * Deletes the Role object by id.
     * 
     * @param id The Role id.
     */
    void deleteById(Long id);
    
    /**
     * 
     * @param role The Role object.
     */
    void delete(Role role);

	/**
	 * 
	 * @param role The Role object.
	 * @param roleDto Role DTO.
	 */
    void update(Role role, RoleDto roleDto);

	/**
	 * 
	 * @param id The Role id.
	 * @return Optional<Role> object.
	 */
    Optional<Role> findById(Long id);


	/**
	 * 
	 * @param userid
	 * @return
	 */
    List<Role> findRolesByUserId(Long userid);

	/**
	 * 
	 * @param user
	 * @param role
	 */
    void removeUserFromRole(User user, Role role);

	/**
	 * 
	 * @param user
	 * @param role
	 */
    void addUserRole(User user, Role role);
}
