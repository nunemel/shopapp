package com.egs.shopapp.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import com.egs.shopapp.model.Product;
import com.egs.shopapp.model.User;

/**
 * Product DTO.
 * 
 * @author Nune
 *
 */
@Validated
public class ProductDto extends RepresentationModel<ProductDto> {
	
	@Size(message = "Currency must be in ISO 4217 format", min = 3, max = 3)
    private String currency;
		
    public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

    @NotEmpty(message = "name is empty")
	@NotNull(message = "name is null")
	private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 3, fraction = 2)
    private BigDecimal price;
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    private String description;
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
	@DecimalMin(value = "0")
    private Integer quantity;
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	/**
     * 
     * @return Product from DTO.
     */
	public Product getCreateProductFromDto(User user){
    	
		Product product = new Product();
        
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
        product.setQuantity(quantity);
        product.setUser(user);
                     
        return product;
    }
	
	public Product getUpdateProductFromDto(Product product, User user){
        
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
        product.setQuantity(quantity);
        product.setUser(user);
                     
        return product;
    }
}
