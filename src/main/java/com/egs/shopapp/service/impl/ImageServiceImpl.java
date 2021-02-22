package com.egs.shopapp.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.egs.shopapp.dao.ImageDao;
import com.egs.shopapp.model.Image;
import com.egs.shopapp.model.dto.ImageDto;
import com.egs.shopapp.service.ImageService;

@Service(value = "imageService")
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDao imageDao;
	
	@Override
	public Optional<Image> findById(Long id) {
		return imageDao.findById(id);
	}

	@Override
	@Transactional
	public Image save(ImageDto imageDto) {		
		return imageDao.save(imageDto.getCreateImageFromDto());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public void delete(Image image) {
		imageDao.delete(image);
	}

	@PreAuthorize("hasRole('USER, ADMIN')")
	@Override
	public List<Image> findByProductId(Long productId) {
		return imageDao.findByProductId(productId);
	}

	@PreAuthorize("hasAnyRole('USER, ADMIN')")
	@Override
	public List<Image> findAll() {
		return imageDao.findAll();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	@Transactional
	public void update(Image image, ImageDto imageDto) {
		imageDao.save(imageDto.getUpdateImageFromDto(image));
	}
}
