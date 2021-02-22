package com.egs.shopapp.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.egs.shopapp.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
	Page<User> findAll(Pageable page);
	
	//@Query("from User u where u.username = ?1")
	Optional<User> findByUsername(String username);
	
	Optional<User> findById(Long id);

	Optional<User> findByUsernameAndActive(String username, Boolean isActive);
	
	@Modifying
	@Query("update User u set u.active = ?1 where u.id = ?2")
	@Transactional
	void deactivateActivateUser(Boolean isActive, Long id);
	
	Optional<User> findByEmail(String email);
}
