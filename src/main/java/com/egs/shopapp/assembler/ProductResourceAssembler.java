package com.egs.shopapp.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.egs.shopapp.controller.ProductController;
import com.egs.shopapp.model.Product;
import com.egs.shopapp.resource.ProductResource;

import io.jsonwebtoken.lang.Collections;

/**
 * Transform {@link Product} into {@link ProductResource} DTO
 *
 * @author Nune Melikyan
 */
@Component
public class ProductResourceAssembler extends ResourceAssemblerSupport<Product, ProductResource> {

    @Autowired
    private CategoryResourceAssembler categoryResourceAssembler;

    public ProductResourceAssembler() {
        super(ProductController.class, ProductResource.class);
    }

    @Override
    protected ProductResource instantiateResource(Product entity) {
        return new ProductResource(
            entity.getName(),
            entity.getPrice(),
            !Collections.isEmpty(entity.getCategories()) ? categoryResourceAssembler.toResources(entity.getCategories()) : null,
            entity.getUser().getUsername(),
            entity.getDescription(),
            entity.getQuantity()
        );
    }

    @Override
    public ProductResource toResource(Product entity) {
        return createResourceWithId(entity.getId(), entity);
    }

}
