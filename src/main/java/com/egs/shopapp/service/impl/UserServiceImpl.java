package com.egs.shopapp.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.egs.shopapp.dao.UserDao;
import com.egs.shopapp.exception.NotFoundException;
import com.egs.shopapp.model.Address;
import com.egs.shopapp.model.Role;
import com.egs.shopapp.model.User;
import com.egs.shopapp.model.dto.AddressDto;
import com.egs.shopapp.model.dto.UserDto;
import com.egs.shopapp.service.RoleService;
import com.egs.shopapp.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userDao.findByUsername(username).orElseThrow(() -> new NotFoundException("username:" + username + "  or password"));
      
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
             
         List<Role> roles = roleService.findRolesByUserId(user.getId());
         
         
         if (roles == null || roles.isEmpty()) {
        	 // if no role add user role
        	 Role role = roleService.findByName("USER").orElseThrow(() -> new NotFoundException("role"));
        	 roles.add(role);
         }
           
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        
       return authorities;
    }
    
    @Transactional
    @Override
    public User getCurrentUser() {
    	org.springframework.security.core.userdetails.User userSec = (org.springframework.security.core.userdetails.User)
    			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	return  userDao.findByUsername(userSec.getUsername())
				.orElseThrow(() -> new NotFoundException("current username"));
    }

    //@PreAuthorize("hasAnyRole('USER, ADMIN')")
    @Override
    public List<User> findAll() {  
        return userDao.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public User save(UserDto user) {

        User nUser = user.getCreateUserFromDto();   
        Optional<Role> role = roleService.findByName("USER");
        nUser.addRole(role.get());
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        return userDao.save(nUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
	@Override
	public void deactivateActivateUser(Boolean isActive, Long id) {			
		userDao.deactivateActivateUser(isActive, id);
	}

	//@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@Override
	public Optional<User> findById(Long id) {
		return userDao.findById(id); 
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@Override
	public void update(User user, UserDto userDto) {
		userDao.save(userDto.getUpdateUserFromDto(user));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

	//@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@Override
	public Optional<User> findByEmail(String email) {		
		return userDao.findByEmail(email);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public void removeUserAddress(User user, Address address) {
		user.getAddresses().remove(address);
        userDao.save(user);
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@Override
	@Transactional
	public void addUserAddress(User user, AddressDto addressDto) {
		user.getAddresses().add(addressDto.getCreateAddressFromDto());
		userDao.save(user);
	}
}
