package com.egs.shopapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egs.shopapp.assembler.RoleResourceAssembler;
import com.egs.shopapp.model.Role;
import com.egs.shopapp.model.User;
import com.egs.shopapp.service.RoleService;
import com.egs.shopapp.service.UserService;

@RestController
@RequestMapping(path = "/users/{userid}/roles")
public class UserRoleController {
	
	@Autowired
    private UserService userService;
		
	@Autowired
    private RoleService roleService;
	
	@Autowired
    private RoleResourceAssembler roleResourceAssembler;
	
    @RequestMapping( path = "/{roleid}", method = RequestMethod.POST)
    public ResponseEntity<?> addUserRole(@PathVariable Long userid, @PathVariable Long roleid) {
    	
    	Optional<User> user = userService.findById(userid);
    	
    	if (user.isEmpty()) {
        	return ResponseEntity.ok("No user found by userid:" + userid);
        }   
    	
    	final Optional<Role> role = roleService.findById(roleid);

        if (role.isEmpty()) {
        	return ResponseEntity.ok("No role found by id:" + roleid);
        }
    	
        roleService.addUserRole(user.get(), role.get());
        
        return ResponseEntity.status(HttpStatus.CREATED)
        		.body("Role successfully added to user:" + userid +  ".");
    }
   
    @RequestMapping(path = "/{roleid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRoleFromUser(@PathVariable Long userid, 
    		@PathVariable Long roleid) {
    	
    	Optional<User> user = userService.findById(userid);
    	
    	if (user.isEmpty()) {
        	return ResponseEntity.ok("No user found by id:" + userid);
        }
    	
    	final Optional<Role> role = roleService.findById(roleid);

        if (role.isEmpty()) {
        	return ResponseEntity.ok("No role found by id:" + roleid);
        }
        
        roleService.removeUserFromRole(user.get(), role.get());
        
        return ResponseEntity.ok("Role " + roleid + " successfully deleted from user " + userid + ".");
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUserRoles(@PathVariable Long userid) {
    	
    	Optional<User> user = userService.findById(userid);
    	
    	if (user.isEmpty()) {
        	return ResponseEntity.ok("No user found by id:" + userid);
        } 
    	
    	List<Role> roles = roleService.findRolesByUserId(userid);
    	
    	if (roles == null || roles.isEmpty()) {
        	return ResponseEntity.ok("There is no roles for user " +  userid + ".");
        }
             	
    	return ResponseEntity.ok(roleResourceAssembler.toResources(roles));
    }
}
