package com.egs.shopapp.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.egs.shopapp.controller.RoleController;
import com.egs.shopapp.model.Role;
import com.egs.shopapp.resource.RoleResource;


/**
 * Transform {@link Role} into {@link RoleResource} DTO
 *
 * @author Nune Melikyan
 */
@Component
public class RoleResourceAssembler extends ResourceAssemblerSupport<Role, RoleResource> {

    public RoleResourceAssembler() {
        super(RoleController.class, RoleResource.class);
    }

    @Override
    protected RoleResource instantiateResource(Role entity) {
        return new RoleResource(
            entity.getName(),
            entity.getDescription()
        );
    }

	@Override
	public RoleResource toResource(Role entity) {
		return createResourceWithId(entity.getId(), entity);
	}
}
