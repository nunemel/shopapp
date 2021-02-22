package com.egs.shopapp.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.egs.shopapp.controller.AddressController;
import com.egs.shopapp.model.Address;
import com.egs.shopapp.resource.AddressResource;

/**
 * Transform {@link Address} into {@link AddressResource} DTO
 *
 * @author Nune 
 */
@Component
public class AddressResourceAssembler extends ResourceAssemblerSupport<Address, AddressResource> {

    public AddressResourceAssembler() {
        super(AddressController.class, AddressResource.class);
    }

    @Override
    protected AddressResource instantiateResource(Address entity) {
        return new AddressResource(
        	entity.getStreet(),
            entity.getCity(),        
            entity.getCountry(),
            entity.getZip(),
            entity.getPhone(),          
            entity.getFax()
        );
    }

	@Override
	public AddressResource toResource(Address entity) {
		return createResourceWithId(entity.getId(), entity);
	}
}

