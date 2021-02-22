package com.egs.shopapp.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.egs.shopapp.dao.ProductDao;
import com.egs.shopapp.model.Category;
import com.egs.shopapp.model.Image;
import com.egs.shopapp.model.Product;
import com.egs.shopapp.model.User;
import com.egs.shopapp.model.dto.ImageDto;
import com.egs.shopapp.model.dto.ProductDto;
import com.egs.shopapp.service.ProductService;
import com.egs.shopapp.service.UserService;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
    private ProductDao productDao;
	
	@Autowired
    private UserService userService;
	

	@PreAuthorize("hasAnyRole('USER, ADMIN')")
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @PreAuthorize("hasAnyRole('USER, ADMIN')")
    @Override
    public List<Product> findAllByCategory(Category category) {
        return productDao.findByAssociatedWithCategory(category.getId());
    }

    @PreAuthorize("hasAnyRole('USER, ADMIN')")
    @Override
    public Optional<Product> getProductById(Long id) {
        return productDao.findById(id);
    }

	@PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public Product save(ProductDto productDto, User user) {
        Product product = productDto.getCreateProductFromDto(user);
        return productDao.save(product);
    }
  
	@PreAuthorize("hasRole('ADMIN') or principal.id == #product.getUser().getId()")
    @Transactional
    @Override
    public void update(Product product, ProductDto productDto, User user) {
    	
        productDao.save(productDto.getUpdateProductFromDto(product, user));
    }

    @PreAuthorize("hasRole('ADMIN') or principal.id == #product.getUser().getId()")
    @Transactional
    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }

    @PreAuthorize("hasAnyRole('USER, ADMIN')")
    @Override
	@Transactional
    public boolean hasCategory(Product product, Category category) {
        return product.getCategories().contains(category);
    }

    @PreAuthorize("hasRole('ADMIN') or principal.id == #product.getUser().getId()")
    @Override
	@Transactional
    public void addCategory(Product product, Category category) {
        product.getCategories().add(category);
        productDao.save(product);
    }

    @PreAuthorize("hasRole('ADMIN') or principal.id == #product.getUser().getId()")
    @Transactional
    @Override
    public void removeCategory(Product product, Category category) {
        product.getCategories().remove(category);
        productDao.save(product);
    }

    @PreAuthorize("hasAnyRole('USER, ADMIN')")
    @Override
    public boolean hasProductsAssociated(Category category) {
        return productDao.countByAssociatedWithCategory(category.getId()) > 0;
    }

    @PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public List<Product> findByPriceRange(BigDecimal since, BigDecimal until) {
		return productDao.findAllBetweenPrices(since, until);
	}

    @PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public List<Product> findByPrice(BigDecimal price) {
		return productDao.findByPrice(price);
	}
		
    @PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	@Transactional
	public Product addImageProduct(ImageDto imageDto, Product product) {
		product.getImages().add(imageDto.getCreateImageFromDto());
		return productDao.save(product);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public void removeImageProduct(Image image, Product product) {
		product.getImages().remove(image);
		productDao.save(product);
	}

	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public Optional<Product> findById(Long productid) {
		return productDao.findById(productid);
	}

	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public List<Image> findImagesByProductId(Long productid) {
		return productDao.findImagesById(productid);
	}

	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public List<Product> findByExample(ProductDto productDto) {
		
		Product product = productDto.getCreateProductFromDto(userService.getCurrentUser());
		Example<Product> example = Example.of(product);
		
		return productDao.findAll(example);
	}

	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public List<Product> findProductsByRating(Integer rating) {
		
		return productDao.findByReviews_Rating(rating);
	}
}
