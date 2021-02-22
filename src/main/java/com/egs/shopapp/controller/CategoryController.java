package com.egs.shopapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egs.shopapp.assembler.CategoryResourceAssembler;
import com.egs.shopapp.model.Category;
import com.egs.shopapp.model.dto.CategoryDto;
import com.egs.shopapp.service.CategoryService;

/**
 * Endpoint for categories.
 *
 * @author Nune
 */
@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CategoryResourceAssembler categoryResourceAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> retrieveAllCategories() {
      
        final List<Category> categories = categoryService.findAll();
        
        if (categories == null || categories.isEmpty()) {
        	return ResponseEntity.ok("No categories found.");
        } 
         
        return ResponseEntity.ok(categoryResourceAssembler.toResources(categories));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> retrieveCategory(@PathVariable Long id) {
       
    	final Optional<Category> category = categoryService.findById(id);

        if (category.isEmpty()) {
        	return ResponseEntity.ok("No category found by id:" + id);
        }

        return ResponseEntity.ok(categoryResourceAssembler.toResource(category.get()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDto categoryDto) {

    	Category category = categoryService.save(categoryDto);
    	
        return ResponseEntity.ok(categoryResourceAssembler.toResource(category));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDto categoryDto) {
        
        final Optional<Category> category = categoryService.findById(id);

        if (category.isEmpty()) {
        	return ResponseEntity.ok("No category found by id:" + id);
        }
     
        categoryService.update(category.get(), categoryDto);

        return ResponseEntity.ok("Category " + id + " successfully updated.");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
 
    	final Optional<Category> category = categoryService.findById(id);

        if (category.isEmpty()) {
        	return ResponseEntity.ok("No category found by id:" + id);
        }
        
        categoryService.delete(category.get());

        return ResponseEntity.ok("Category " + id + " successfully removed.");
    }
}
