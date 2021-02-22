package com.egs.shopapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egs.shopapp.model.Image;

@Repository
public interface ImageDao extends JpaRepository<Image, Long>  {

	List<Image> findByProductId(Long productId);
}
