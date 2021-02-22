package com.egs.shopapp.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.egs.shopapp.dao.RoleDao;
import com.egs.shopapp.model.Role;
import com.egs.shopapp.model.User;
import com.egs.shopapp.model.dto.RoleDto;
import com.egs.shopapp.service.RoleService;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    //@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public Optional<Role> findByUserId(Long userId) {	
		return roleDao.findById(userId);
	}

	//@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	//@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public Optional<Role> findByName(String name) {
		return roleDao.findByName(name);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public Role save(RoleDto roleDto) {
		return roleDao.save(roleDto.getCreateRoleFromDto());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public void update(Role role, RoleDto roleDto) {
		roleDao.save(roleDto.getUpdateRoleFromDto(role));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public void deleteById(Long id) {
		roleDao.deleteById(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public void delete(Role role) {
		roleDao.delete(role);
	}

	//@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public Optional<Role> findById(Long id) {
		return roleDao.findById(id);
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
    public void addUserRole(User user, Role role) {
    	role.addUser(user);
        roleDao.save(role);
    }	

    @PreAuthorize("hasRole('ADMIN')")
	@Transactional
    @Override
    public void removeUserFromRole(User user, Role role) {
        role.removeUser(user);
        roleDao.save(role);
    }

	//@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public List<Role> findRolesByUserId(Long userid) {
		return roleDao.findByUsers_Id(userid);
	}
}