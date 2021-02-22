package com.egs.shopapp.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.egs.shopapp.dao.AddressDao;
import com.egs.shopapp.model.Address;
import com.egs.shopapp.model.dto.AddressDto;
import com.egs.shopapp.service.AddressService;

@Service(value = "addressService")
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;
	
	@PreAuthorize("hasRole('USER')")
	@Override
	public Optional<Address> findById(Long id) {
		return addressDao.findById(id);
	}

	@PreAuthorize("hasRole('USER')")
	@Override
	public List<Address> findByUserId(Long userId) {
		return addressDao.findByUserId(userId);
	}

	@PreAuthorize("hasRole('USER')")
	@Override
	public List<Address> findAll() {
		return addressDao.findAll();
	}
		
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public Address save(AddressDto addressDto) {
		Address address = addressDto.getCreateAddressFromDto();
		
		return addressDao.save(address);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public void update(Address address, AddressDto addressDto) {
		addressDao.save(addressDto.getUpdateAddressFromDto(address));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public void delete(Address address) {
		addressDao.delete(address);
	}
}
