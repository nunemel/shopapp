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

import com.egs.shopapp.assembler.ProductResourceAssembler;
import com.egs.shopapp.model.Category;
import com.egs.shopapp.model.Product;
import com.egs.shopapp.service.CategoryService;
import com.egs.shopapp.service.ProductService;


/**
 * Endpoint for categories and products association.
 *
 * @author Nune 
 */
@RestController
@RequestMapping(path = "/categories/{categoryid}/products")
public class CategoryProductController {

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductResourceAssembler productResourceAssembler;
   
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> retrieveAllProducts(@PathVariable Long categoryid) {
   
    	final Optional<Category> category = categoryService.findById(categoryid);

        if (category.isEmpty()) {
        	return ResponseEntity.ok("No category found by id:" + categoryid);
        }

        final List<Product> products = productService.findAllByCategory(category.get());

        return ResponseEntity.ok(productResourceAssembler.toResources(products));
    }

    @RequestMapping(path = "/{productid}", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@PathVariable Long categoryid, @PathVariable Long productid) {

    	final Optional<Category> category = categoryService.findById(categoryid);

        if (category.isEmpty()) {
        	return ResponseEntity.ok("No category found by id:" + categoryid);
        }

        final Optional<Product> product = productService.findById(productid);

        if (product.isEmpty()) {
        	return ResponseEntity.ok("No product found by id:" + productid);
        }

        if (productService.hasCategory(product.get(), category.get())) {
            return ResponseEntity.ok("product " + productid + " already contains category " + categoryid);
        }

        productService.addCategory(product.get(), category.get());

        return ResponseEntity.status(HttpStatus.CREATED)
        		.body("Product " + productid + " successfully added to " + categoryid + " category");
    }

    @RequestMapping(path = "/{productid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeProduct(@PathVariable Long categoryid, @PathVariable Long productid) {
 
    	final Optional<Category> category = categoryService.findById(categoryid);

        if (category.isEmpty()) {
        	return ResponseEntity.ok("No category found by id:" + categoryid);
        }

        final Optional<Product> product = productService.findById(productid);

        if (product.isEmpty()) {
        	return ResponseEntity.ok("No product found by id:" + productid);
        }

        if (!productService.hasCategory(product.get(), category.get())) {
        	ResponseEntity.ok("product " + productid + " does not contain category " + categoryid);
        }

        productService.removeCategory(product.get(), category.get());

        return ResponseEntity.ok("Product successfully removed from category");
    }

}
