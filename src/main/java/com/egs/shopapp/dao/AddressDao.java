package com.egs.shopapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egs.shopapp.model.Address;

public interface AddressDao extends JpaRepository<Address, Long> {

	/**
	 * 
	 * @param userId The User id.
	 * @return
	 */
	List<Address> findByUserId(Long userId);

	/**
	 * 
	 * @param userid The User id.
	 * @return List of addresses of User.
	 */
	List<Address> findAddressesByUserId(Long userid);

}
