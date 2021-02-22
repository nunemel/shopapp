package com.egs.shopapp.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.egs.shopapp.controller.CategoryController;
import com.egs.shopapp.controller.CategoryProductController;
import com.egs.shopapp.controller.CategorySubcategoryController;
import com.egs.shopapp.model.Category;
import com.egs.shopapp.resource.CategoryResource;
import com.egs.shopapp.service.ProductService;

/**
 * Transform {@link Category} into {@link CategoryResource} DTO
 *
 * @author Nune Melikyan
 */
@Component
public class CategoryResourceAssembler extends ResourceAssemblerSupport<Category, CategoryResource> {

    @Autowired
    private ProductService productService;

    public CategoryResourceAssembler() {
        super(CategoryController.class, CategoryResource.class);
    }

    @Override
    protected CategoryResource instantiateResource(Category entity) {
        return new CategoryResource(entity.getName());
    }

    @Override
    public CategoryResource toResource(Category entity) {
        CategoryResource resource = createResourceWithId(entity.getId(), entity);
        if (entity.getParent() != null) {
            resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CategoryController.class).retrieveCategory(entity.getParent().getId())).withRel("parent"));
        }
        if (entity.getChildCategories() != null) {
            resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CategorySubcategoryController.class).retrieveAllSubcategories(entity.getId())).withRel("subcategories"));
        }
        if (productService.hasProductsAssociated(entity)) {
            resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CategoryProductController.class).retrieveAllProducts(entity.getId())).withRel("products"));
        }

        return resource;
    }
}
