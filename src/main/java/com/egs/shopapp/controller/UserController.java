package com.egs.shopapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egs.shopapp.assembler.UserResourceAssembler;
import com.egs.shopapp.config.TokenProvider;
import com.egs.shopapp.model.User;
import com.egs.shopapp.model.dto.AuthToken;
import com.egs.shopapp.model.dto.LoginDto;
import com.egs.shopapp.model.dto.UserDto;
import com.egs.shopapp.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserResourceAssembler userResourceAssembler;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginDto loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDto userDto){

    	if (!userService.findByUsername(userDto.getUsername()).isEmpty()) {
    		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This username already exists.");
    	}
    	
    	if (!userService.findByEmail(userDto.getEmail()).isEmpty()) {
    		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This email already exists.");
    	}
    	
    	User user = userService.save(userDto); 
    	
    	return ResponseEntity.ok(userResourceAssembler.toResource(user));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> findAllUsers() {
    	
    	List<User> users = userService.findAll();
    	
    	if (users == null || users.isEmpty()) {
        	return ResponseEntity.ok("No users found.");
        } 
    	
    	return ResponseEntity.ok(userResourceAssembler.toResources(users));
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> deactivateActivateUser(@RequestParam("active") Boolean isActive, @PathVariable("id") Long id) {
    	userService.deactivateActivateUser(isActive, id);
    	
    	return ResponseEntity.ok("User state changed successfuly.");
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findOneUser(@PathVariable("id") Long id) {
    	
    	Optional<User> user = userService.findById(id);
    	
    	if (user.isEmpty()) {
        	return ResponseEntity.ok("No user found by id:" + id);
        }
    	
    	return ResponseEntity.ok(userResourceAssembler.toResource(user.get()));
    }
}
