package com.egs.shopapp.service;

import java.util.List;
import java.util.Optional;

import com.egs.shopapp.model.Address;
import com.egs.shopapp.model.dto.AddressDto;

/**
 * Service for {@link Address} entity
 *
 * @author Nune Melikyan
 */
public interface AddressService {
	
	/**
	 * 
	 * @param id The Address id.
	 * @return The Optional<Address> object.
	 */
	public Optional<Address> findById(Long id);

	/**
	 * 
	 * @param The Address id.
	 * @return Set of Addresses by user id.
	 */
	List<Address> findByUserId(Long userId);
	
	/**
	 * 
	 * @return
	 */
	public List<Address> findAll();
	
	/**
	 * 
	 * @param addressDto The Address DTO.
	 * @return The Address object.
	 */
	public Address save(AddressDto addressDto);

	/**
	 * 
	 * @param address The Address object.
	 * @param addressDto The Address DTO.
	 */
	public void update(Address address, AddressDto addressDto);	
	
	/**
	 * Delete the Address object.
	 * 
	 * @param address The Address object.
	 * 
	 */
	void delete(Address address);
}
