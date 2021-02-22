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
import com.egs.shopapp.model.dto.AddressDto;
import com.egs.shopapp.service.AddressService;


/**
 * Endpoint for addresses.
 *
 * @author Nune
 */
@RestController
@RequestMapping(path = "/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;
    
    @Autowired
    private AddressResourceAssembler addressResourceAssembler;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> retrieveAllCategories() {
     
        final List<Address> addresses = addressService.findAll();

        return ResponseEntity.ok(addresses);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> retrieveAddress(@PathVariable Long id) {

        final Optional<Address> address = addressService.findById(id);
        
        if (address.isEmpty()) {
        	return ResponseEntity.ok("No Address found by id:" + id);
        }
       
        return ResponseEntity.ok(addressResourceAssembler.toResource(address.get()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAddress(@RequestBody @Valid AddressDto addressDto) {
     
    	Address address = addressService.save(addressDto);
    	
        return ResponseEntity.status(HttpStatus.CREATED).body(addressResourceAssembler.toResource(address));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @RequestBody @Valid AddressDto addressDto) {
      
        final Optional<Address> address = addressService.findById(id);
        
        if (address.isEmpty()) {
        	return ResponseEntity.ok("No Address found by id:" + id);
        }

        addressService.update(address.get(), addressDto);

        return  ResponseEntity.ok("Address " + id + " successfully updated");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
    
        final Optional<Address> address = addressService.findById(id);

        if (address.isEmpty()) {
        	return ResponseEntity.ok("No address found by id:" + id);
        }
       
        addressService.delete(address.get());

        return ResponseEntity.ok("Address " + id + " successfully removed.");
    }
}
