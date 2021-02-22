package com.egs.shopapp.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egs.shopapp.assembler.CategoryResourceAssembler;
import com.egs.shopapp.model.Category;
import com.egs.shopapp.service.CategoryService;

/**
 * Endpoint for categories and subcategories.
 *
 * @author Nune 
 */
@RestController
@RequestMapping(path = "/categories/{parentid}/subcategories")
public class CategorySubcategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CategoryResourceAssembler categoryResourceAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> retrieveAllSubcategories(@PathVariable Long parentid) {
     
    	final Optional<Category> parent = categoryService.findById(parentid);
    	
    	if (parent.isEmpty()) {
        	return ResponseEntity.ok("No parent category found by id:" + parentid);
        }
               
        final Set<Category> subcategories = parent.get().getChildCategories();
        
        if (subcategories == null || subcategories.isEmpty()) {
        	return ResponseEntity.ok("No subcategories found for parent category " + parentid + ".");
        }

        return ResponseEntity.ok(categoryResourceAssembler.toResources(subcategories));
    }

    @RequestMapping(path = "/{childid}", method = RequestMethod.POST)
    public ResponseEntity<?> addSubcategory(@PathVariable Long parentid, @PathVariable Long childid) {
        
    	final Optional<Category> parent = categoryService.findById(parentid);
    	
    	if (parent.isEmpty()) {
        	return ResponseEntity.ok("No parent category found by id:" + parentid);
        }
       
    	final Optional<Category> child = categoryService.findById(childid);
    	
    	if (child.isEmpty()) {
        	return ResponseEntity.ok("No child category found by id:" + childid);
        }
    
        if (categoryService.isChildCategory(child.get(), parent.get())) {
        	return ResponseEntity.ok("category " + parentid + " already contains subcategory " + childid);
        }

        categoryService.addChildCategory(child.get(), parent.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResourceAssembler.toResource(parent.get()));
    }

    @RequestMapping(path = "/{childid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeSubcategory(@PathVariable Long parentid, @PathVariable Long childid) {

    	final Optional<Category> parent = categoryService.findById(parentid);
    	
    	if (parent.isEmpty()) {
        	return ResponseEntity.ok("No parent category found by id:" + parentid);
        }
       
    	final Optional<Category> child = categoryService.findById(childid);
    	
    	if (parent.isEmpty()) {
        	return ResponseEntity.ok("No child category found by id:" + childid);
        }

        if (!categoryService.isChildCategory(child.get(), parent.get())) {
        	return ResponseEntity.ok("category " + parentid + " does not contain subcategory " + childid);
        }

        categoryService.removeChildCategory(child.get(), parent.get());

        return ResponseEntity.ok("Child category " + childid + " succcessfully removed");
    }
}
