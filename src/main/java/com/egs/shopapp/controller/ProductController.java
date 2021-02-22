package com.egs.shopapp.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egs.shopapp.assembler.ProductResourceAssembler;
import com.egs.shopapp.model.Product;
import com.egs.shopapp.model.User;
import com.egs.shopapp.model.dto.ProductDto;
import com.egs.shopapp.service.ProductService;
import com.egs.shopapp.service.UserService;


/**
 * Endpoint for product management.
 *
 * @author Nune 
 */
@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductResourceAssembler productResourceAssembler;
    
    @Autowired
    private UserService userService;
    
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> retrieveAllProducts() {
        final List<Product> products = productService.findAll();
        
        if (products.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products in the system.");
    	}
        
        return ResponseEntity.ok(productResourceAssembler.toResources(products));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> retrieveProduct(@PathVariable Long id) {
     
    	final Optional<Product> product = productService.findById(id);

        if (product.isEmpty()) {
        	return ResponseEntity.ok("No product found by id:" + id);
        }

        return ResponseEntity.ok(productResourceAssembler.toResource(product.get()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDto productDto) {
    	
        User currentUser = userService.getCurrentUser();
     	
    	Product product = productService.save(productDto, currentUser);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(productResourceAssembler.toResource(product));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) {
        
        User currentUser = userService.getCurrentUser();
        
        final Optional<Product> product = productService.findById(id);

        if (product.isEmpty()) {
        	return ResponseEntity.ok("No product found by id:" + id);
        }
                
        productService.update(product.get(), productDto, currentUser);
        
        return ResponseEntity.ok(productResourceAssembler.toResource(product.get()));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
            	 
        final Optional<Product> product = productService.findById(id);

        if (product.isEmpty()) {
        	return ResponseEntity.ok("No product found by id:" + id);
        }

        productService.delete(product.get());

        return ResponseEntity.ok("Product " + id + " successfully deleted.");
    }
    
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ResponseEntity<?> findProductByPriceRange(@RequestParam("price_since") BigDecimal priceSince,
    		@RequestParam("price_until") BigDecimal priceUntil) {
    	
    	List<Product> products = productService.findByPriceRange(priceSince, priceUntil);
    	 
        if (products == null || products.isEmpty()) {
        	return ResponseEntity.ok("No products found.");
        } 
    	
    	return ResponseEntity.ok(productResourceAssembler.toResources(products));
    }
    
    @RequestMapping(path = "/{id}/search-example", method = RequestMethod.GET)
    public ResponseEntity<?> findProductByExample(@RequestBody ProductDto productDto, Pageable pageable) {
    	
    	List<Product> products = productService.findByExample(productDto);
    	
    	if (products.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product by this pattern.");
    	}
    	    	
    	return ResponseEntity.status(HttpStatus.FOUND).body(productResourceAssembler.toResources(products));
    }
    
    @RequestMapping(path = "/{id}/search-rating", method = RequestMethod.GET)
    public ResponseEntity<?> findProductByRating(@RequestParam("rating") Integer rating) {
    	
    	List<Product> products = productService.findProductsByRating(rating);
    	 
        if (products == null || products.isEmpty()) {
        	return ResponseEntity.ok("No products found.");
        } 
    	
    	return ResponseEntity.ok(productResourceAssembler.toResources(products));
    }
    
}
