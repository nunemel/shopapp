package com.egs.shopapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egs.shopapp.assembler.AddressResourceAssembler;
import com.egs.shopapp.model.Address;
import com.egs.shopapp.model.User;
import com.egs.shopapp.model.dto.AddressDto;
import com.egs.shopapp.service.AddressService;
import com.egs.shopapp.service.UserService;

@RestController
@RequestMapping(path = "/users/{userid}/addresses")
public class UserAddressController {
	@Autowired
    private UserService userService;
		
	@Autowired
    private AddressService addressService;
	
    @Autowired
    private AddressResourceAssembler addressResourceAssembler;
	
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUserAddress(@PathVariable Long userid, 
    		@RequestBody @Valid AddressDto addressDto) {
    	
    	Optional<User> user = userService.findById(userid);
    	
    	if (user.isEmpty()) {
        	return ResponseEntity.ok("No user found by id:" + userid);
        } 
    	
        userService.addUserAddress(user.get(), addressDto);
        
        return ResponseEntity.status(HttpStatus.CREATED)
        		.body("User " + userid + " address successfully created.");
    }
   
    @RequestMapping(path = "/{addressid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserAddress(@PathVariable Long userid, 
    		@PathVariable Long addressid) {
    	
    	Optional<User> user = userService.findById(userid);
    	
    	if (user.isEmpty()) {
        	return ResponseEntity.ok("No user found by userid:" + userid);
        } 
        
        final Optional<Address> address = addressService.findById(addressid);
        
        if (address.isEmpty()) {
        	return ResponseEntity.ok("No address found by id:" + addressid);
        }
        
        userService.removeUserAddress(user.get(), address.get());
        
        return ResponseEntity.ok("Address " + addressid + " successfully removed from" + userid + "user");
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUserAddresss(@PathVariable Long userid) {
    	
    	Optional<User> user = userService.findById(userid);
    	
    	if (user.isEmpty()) {
        	return ResponseEntity.ok("No user found by userid:" + userid);
        }  
    	
    	List<Address> addresses = addressService.findByUserId(userid);
    	
    	 if (addresses == null || addresses.isEmpty()) {
         	return ResponseEntity.ok("No addresses found by userid: " + userid);
         }
            
    	return ResponseEntity.ok(addressResourceAssembler.toResources(addresses));
    }

}
