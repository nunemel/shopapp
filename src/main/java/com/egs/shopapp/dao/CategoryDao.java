package com.egs.shopapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egs.shopapp.model.Category;

/**
 * The Category DAO.
 * 
 * @author Nune
 *
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
   
    String GET_RECURSIVELY_ALL_SUBCATEGORIES_SQL = "WITH RECURSIVE ALL_SUBCATEGORIES(ID, PARENT_ID) AS (select c.id, c.parent_id from e_category c where c.parent_id is null union all select c.id, c.parent_id from ALL_SUBCATEGORIES inner join e_category c on ALL_SUBCATEGORIES.id = c.parent_id) select id, parent_id from ALL_SUBCATEGORIES";
    
    Optional<Category> findById(Long id);
    
    List<Category> findAll();
}
