package com.egs.shopapp.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.egs.shopapp.controller.ReviewController;
import com.egs.shopapp.model.Review;
import com.egs.shopapp.resource.ReviewResource;


/**
 * Transform {@link Review} into {@link ReviewResource} DTO
 *
 * @author Nune Melikyan
 */
@Component
public class ReviewResourceAssembler extends ResourceAssemblerSupport<Review, ReviewResource> {

    public ReviewResourceAssembler() {
        super(ReviewController.class, ReviewResource.class);
    }

    @Override
    protected ReviewResource instantiateResource(Review entity) {
        return new ReviewResource(
            entity.getRating(),
            entity.getComment(),
            entity.getProduct().getId(),
            entity.getCreatedBy()
        );
    }

	@Override
	public ReviewResource toResource(Review entity) {
		return createResourceWithId(entity.getId(), entity);
	}
}
