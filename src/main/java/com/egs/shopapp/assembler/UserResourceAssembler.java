package com.egs.shopapp.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.egs.shopapp.controller.UserController;
import com.egs.shopapp.model.User;
import com.egs.shopapp.resource.UserResource;

/**
 * Transform {@link User} into {@link UserResource} DTO
 *
 * @author Nune Melikyan
 */
@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    protected UserResource instantiateResource(User entity) {
        return new UserResource(
            entity.getUsername(),
            entity.getPassword(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getEmail()
        );
    }

	@Override
	public UserResource toResource(User entity) {
		return createResourceWithId(entity.getId(), entity);
	}
}

