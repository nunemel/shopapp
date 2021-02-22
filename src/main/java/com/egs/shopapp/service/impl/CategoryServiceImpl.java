package com.egs.shopapp.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.egs.shopapp.dao.CategoryDao;
import com.egs.shopapp.model.Category;
import com.egs.shopapp.model.dto.CategoryDto;
import com.egs.shopapp.service.CategoryService;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;
	
	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public Optional<Category> findById(Long id) {
		return categoryDao.findById(id);
	}

	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public Category save(CategoryDto categoryDto) {
		return categoryDao.save(categoryDto.getCreateCategoryFromDto());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public Category update(Category category, CategoryDto categoryDto) {
		return categoryDao.save(categoryDto.getUpdateCategoryFromDto(category));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public void delete(Category category) {
		categoryDao.delete(category);
	}
	
	@PreAuthorize("hasAnyRole('USER, ADMIN')")
    @Transactional
    @Override
    public boolean isChildCategory(Category category, Category parent) {
		
		if (category.getParent() == null) {
			return false;
		}
		
        return category.getParent().equals(parent);
    }

	@PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public void addChildCategory(Category category, Category parent) {
        category.setParent(parent);
        categoryDao.save(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public void removeChildCategory(Category category, Category parent) {
        category.setParent(null);
        categoryDao.save(category);
    }
}
