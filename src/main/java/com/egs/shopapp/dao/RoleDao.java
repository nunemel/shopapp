package com.egs.shopapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egs.shopapp.model.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long>  {

	Page<Role> findAll(Pageable pageable);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	Optional<Role> findByName(String name);

	/**
	 * 
	 * @param userid
	 * @return
	 */
	List<Role> findByUsers_Id(Long id);

}
