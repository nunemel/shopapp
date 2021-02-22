package com.egs.shopapp.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egs.shopapp.model.Image;
import com.egs.shopapp.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
	
    String GET_PRODUCTS_ASSOCIATED_WITH_CATEGORY_SQL = "select p.* from e_product p inner join e_product_category pc on p.id = pc.product_id where (pc.category_id = ?1 or pc.category_id in (select ac.id from (" + CategoryDao.GET_RECURSIVELY_ALL_SUBCATEGORIES_SQL + ") ac where ac.parent_id = ?1)) ";
    String COUNT_PRODUCTS_ASSOCIATED_WITH_CATEGORY_SQL = "select count(1) from (" + GET_PRODUCTS_ASSOCIATED_WITH_CATEGORY_SQL + ")";

    /**
     * Finds the products associated with a given category.
     *
     * @param categoryId the categoryId to look for
     * @return the list products
     */
    @Query(value = GET_PRODUCTS_ASSOCIATED_WITH_CATEGORY_SQL, 
    		countQuery = COUNT_PRODUCTS_ASSOCIATED_WITH_CATEGORY_SQL, 
    		nativeQuery = true)
    List<Product> findByAssociatedWithCategory(Long categoryId);

    /**
     * Counts the number of products associated with the given category.
     *
     * @param categoryId the category Id to check
     * @return the number of products associated with the category
     */
    @Query(value = COUNT_PRODUCTS_ASSOCIATED_WITH_CATEGORY_SQL, nativeQuery = true)
    Long countByAssociatedWithCategory(Long categoryId);
        
    /**
     * 
     * @param name
     * @param 
     * @return
     */
    List<Product> findByNameContainingIgnoreCase(String name);
    
    /**
     * 
     * @param description
     * @param 
     * @return
     */
    List<Product> findByDescriptionContainingIgnoreCase(String description);
    
    /**
     * 
     * @param quantity
     * @param 
     * @return
     */
    List<Product> findByQuantity(Integer quantity);
    
    /**
     * 
     * @param price
     * @param 
     * @return
     */
    List<Product> findByPrice(BigDecimal price);
    
    /**
     * 
     * @param rating
     * @return
     */
    List<Product> findByReviews_Rating(Integer rating);
        
    /**
     * 
     * @param since
     * @param until
     * @return
     */
    @Query(value = "from Product p where price BETWEEN :fromPrice AND :toPrice")
    List<Product> findAllBetweenPrices(@Param(value = "fromPrice") BigDecimal fromPrice, @Param(value = "toPrice") BigDecimal toPrice);

	/**
	 * 
	 * @param productid
	 * @return
	 */
    List<Image> findImagesById(Long productid);
}
