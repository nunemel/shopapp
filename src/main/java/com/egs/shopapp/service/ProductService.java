package com.egs.shopapp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.egs.shopapp.model.Category;
import com.egs.shopapp.model.Image;
import com.egs.shopapp.model.Product;
import com.egs.shopapp.model.User;
import com.egs.shopapp.model.dto.ImageDto;
import com.egs.shopapp.model.dto.ProductDto;


/**
 * Service for {@link Product} entity
 *
 * @author Nune Melikyan
 */
public interface ProductService {

    /**
     * Gets all products present in the system.
     * 
     * @return The list of products.
     */
	List<Product> findAll();

    /**
     * Gets all products that are associated with the given category.
     * The association can be either directly or indirectly.
     * Please {@see Product} entity for more details.
     *
     * @param category the category to filter
     * @return the list results
     */
    List<Product> findAllByCategory(Category category);

    /**
     * Gets a specific product by looking for its id.
     *
     * @param id the id of the product to look for
     * @return the product (if any)
     */
    Optional<Product> getProductById(Long id);
    
    /**
     * 
     * Creates(if id is null) or updates a Product object (if id exists).
     * If the currency is not 'AMD' then a Currency Exchange
     * will be performed.
     * 
     */
	Product save(ProductDto productDto, User user);    
    
    /**
     * Deletes a product in the system.
     *
     * @param product The Product to delete.
     */
    void delete(Product product);
    
    /**
	  * 
	  * @param product
	  * @param productDto
	  */
    void update(Product product, ProductDto productDto, User user);

    /**
     * Checks whether the product has a given category.
     *
     * @param product the product to check
     * @param category the category to check
     * @return true if the product is associated with the category
     */
    boolean hasCategory(Product product, Category category);

    /**
     * Adds a category to the product.
     *
     * @param product the product to add the category to
     * @param category the category to add
     */
    void addCategory(Product product, Category category);

    /**
     * Removes a category from the product.
     *
     * @param product the product to remove the category from
     * @param category the category to remove
     */
    void removeCategory(Product product, Category category);

    /**
     * Checks whether or not a given category has products associated.
     * The association can be either directly or indirectly.
     * Please {@see Product} entity for more details.
     *
     * @param category the category to check
     */
    boolean hasProductsAssociated(Category category);
      
    /**
     * Finds Product by price.
     * 
     * @param price
     * @return
     */
    List<Product> findByPrice(BigDecimal price);
    
    /**
     * Finds Product by price range.
     * 
     * @param since The starting price.
     * @param until The ending price.
     * @return List<Product> object.
     */
     List<Product> findByPriceRange(BigDecimal since, BigDecimal until);

     /**
      * 
      * @param imageDto
      * @param product
      */
     Product addImageProduct(ImageDto imageDto, Product product);

     /**
      * 
      * @param image The Image object.
      * @param product The Product object.
      */
     void removeImageProduct(Image image, Product product);

     /**
      * 
      * @param productid The Product id.
      * @return Optional<Product> object.
      */
	 Optional<Product> findById(Long productid);

	 /**
	  * 
	  * @param productid
	  * @return
	  */
	 List<Image> findImagesByProductId(Long productid);

	 /**
	  * 
	  * @param productDto The Product DTO.
	  * @return
	  */
	 List<Product> findByExample(ProductDto productDto);
	 

	 /**
	  * 
	  * @param rating
	  * @return
	  */
	 List<Product> findProductsByRating(Integer rating);
	 
}

