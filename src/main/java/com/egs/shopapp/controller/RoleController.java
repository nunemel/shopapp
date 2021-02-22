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

import com.egs.shopapp.assembler.RoleResourceAssembler;
import com.egs.shopapp.model.Role;
import com.egs.shopapp.model.dto.RoleDto;
import com.egs.shopapp.service.RoleService;

/**
 * Endpoint for reviews.
 *
 * @author Nune
 */
@RestController
@RequestMapping(path = "/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private RoleResourceAssembler roleResourceAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> retrieveAllRoles() {
         	
        final List<Role> roles = roleService.findAll();
                   
        if (roles == null || roles.isEmpty()) {
        	return ResponseEntity.ok("There is no roles in the database.");
        }
            
        return ResponseEntity.ok(roleResourceAssembler.toResources(roles));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> retrieveRole(@PathVariable Long id) {
 
    	final Optional<Role> role = roleService.findById(id);

        if (role.isEmpty()) {
        	return ResponseEntity.ok("No role found by id:" + id);
        }

        return ResponseEntity.ok(roleResourceAssembler.toResource(role.get()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createRole(@RequestBody @Valid RoleDto roleDto) {
    	
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(roleDto));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody @Valid RoleDto roleDto) {

    	final Optional<Role> role = roleService.findById(id);

        if (role.isEmpty()) {
        	return ResponseEntity.ok("No role found by id:" + id);
        }

        roleService.update(role.get(), roleDto);

        return ResponseEntity.ok("Role " + id + " successfully updated.");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
    	final Optional<Role> role = roleService.findById(id);

        if (role.isEmpty()) {
        	return ResponseEntity.ok("No role found by id:" + id);
        }

        roleService.delete(role.get());

        return ResponseEntity.ok("Role " + id + " successfully deleted.");
    }
}
