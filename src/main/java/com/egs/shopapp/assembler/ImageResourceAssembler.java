package com.egs.shopapp.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.egs.shopapp.controller.ImageController;
import com.egs.shopapp.model.Image;
import com.egs.shopapp.resource.ImageResource;

@Component
public class ImageResourceAssembler extends ResourceAssemblerSupport<Image, ImageResource> {

    public ImageResourceAssembler() {
        super(ImageController.class, ImageResource.class);
    }

    @Override
    protected ImageResource instantiateResource(Image entity) {
        return new ImageResource(
            entity.getName(),
            entity.getPath(),
            entity.getDescription()
        );
    }

	@Override
	public ImageResource toResource(Image entity) {
		return createResourceWithId(entity.getId(), entity);
	}
}