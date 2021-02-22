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

import com.egs.shopapp.assembler.ImageResourceAssembler;
import com.egs.shopapp.model.Image;
import com.egs.shopapp.model.dto.ImageDto;
import com.egs.shopapp.service.ImageService;

/**
 * Endpoint for images.
 *
 * @author Nune
 */
@RestController
@RequestMapping(path = "/images")
public class ImageController {

    @Autowired
    private ImageService imageService;
    
    @Autowired
    private ImageResourceAssembler imageResourceAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> retrieveAllCategories() {
        
        final List<Image> images = imageService.findAll();

        return ResponseEntity.ok(images);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> retrieveImage(@PathVariable Long id) {

    	final Optional<Image> image = imageService.findById(id);

        if (image.isEmpty()) {
        	return ResponseEntity.ok("No image found by id:" + id);
        }

        return ResponseEntity.ok(imageResourceAssembler.toResource(image.get()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createImage(@RequestBody @Valid ImageDto imageDto) {

    	Image image = imageService.save(imageDto);
    	
        return ResponseEntity.ok(imageResourceAssembler.toResource(image));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestBody @Valid ImageDto imageDto) {

    	final Optional<Image> image = imageService.findById(id);

        if (image.isEmpty()) {
        	return ResponseEntity.ok("No image found by id:" + id);
        }

        imageService.update(image.get(), imageDto);

        return ResponseEntity.ok("Image " + id + " successfully updated.");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {

    	final Optional<Image> image = imageService.findById(id);

        if (image.isEmpty()) {
        	return ResponseEntity.ok("No image found by id:" + id);
        }

        imageService.delete(image.get());

        return ResponseEntity.ok("Image " + id + " successfully removed.");
    }
}
