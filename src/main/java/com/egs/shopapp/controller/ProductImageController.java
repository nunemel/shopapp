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

import com.egs.shopapp.assembler.ImageResourceAssembler;
import com.egs.shopapp.model.Image;
import com.egs.shopapp.model.Product;
import com.egs.shopapp.model.dto.ImageDto;
import com.egs.shopapp.service.ImageService;
import com.egs.shopapp.service.ProductService;

@RestController
@RequestMapping(path = "/products/{productid}/images")
public class ProductImageController {

    @Autowired
    private ImageService imageService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ImageResourceAssembler imageResourceAssembler;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createProductImage(@PathVariable Long productid, 
    		@RequestBody @Valid ImageDto imageDto) {
    	
    	final Optional<Product> product = productService.findById(productid);

        if (product.isEmpty()) {
        	return ResponseEntity.ok("No product found by id:" + productid);
        }     
              
        productService.addImageProduct(imageDto, product.get());
        
        return ResponseEntity.status(HttpStatus.CREATED)
        		.body("Image  successfully added to " + productid + "product");
    }
   
    @RequestMapping(path = "/{imageid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteImageProduct(@PathVariable Long productid, 
    		@PathVariable Long imageid) {
    	
    	final Optional<Product> product = productService.findById(productid);

        if (product.isEmpty()) {
        	return ResponseEntity.ok("No product found by id:" + productid);
        } 
        
        final Optional<Image> image = imageService.findById(imageid);

        if (image.isEmpty()) {
        	return ResponseEntity.ok("No image found by imageid:" + imageid);
        } 
        
        productService.removeImageProduct(image.get(), product.get());
        
        return ResponseEntity.ok("Image " + imageid + " successfully removed from " + productid + "product");
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getProductImages(@PathVariable Long productid) {
    	
    	final Optional<Product> product = productService.findById(productid);

        if (product.isEmpty()) {
        	return ResponseEntity.ok("No product found by productid:" + productid);
        }
    	
        List<Image> images = productService.findImagesByProductId(productid);
        
        if(images == null || images.isEmpty()) {
        	return ResponseEntity.ok("No images found by productid:" + productid);
        }
       
    	return ResponseEntity.ok(imageResourceAssembler.toResources(images));
    }
}

