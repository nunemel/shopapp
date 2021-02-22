package com.egs.shopapp.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * A Product is an entity that represents an article for sale.
 * Products are associated with categories.
 *
 * @author Nune 
 */
@Entity
@Table(name = "e_product")
@EntityListeners(AuditingEntityListener.class)
public class Product extends Persistent<String> {

	@Transient
	private static final long serialVersionUID = 8814393057641174241L;

	public static final String CURRENCY = "AMD";

    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    	
    @DecimalMin("1.00")
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
        
    @Column(name = "description")
    private String description;
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
	@Column(name = "quantity", nullable = false)
    private Integer quantity;
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@ManyToOne
    @JoinColumn(name = "userid", nullable = false, updatable = false)
    private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToMany
    @JoinTable(name = "e_product_category", 
			    joinColumns = @JoinColumn(name = "product_id"), 
			    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<Category>();

	public void setCategories(Set<Category> categories) {
	       this.categories = categories;
	}
	
	public Set<Category> getCategories() {
        return categories;
    }
    
    public void addCategory(Category category) {
        this.categories.add(category);
        category.getProducts().add(this);
    }
 
    public void removeCategory(Category category) {
        this.categories.remove(category);
        category.getProducts().remove(this);
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Review> reviews;
    
    public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@OneToMany( mappedBy = "product", cascade = CascadeType.ALL)
	private Set<Image> images; 
	
    public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	@Override
    public int hashCode() {
        return new HashCodeBuilder()
	        .append(name)
	        .append(price)
	        .append(description)
	        .append(quantity)
	        .toHashCode();
    }
	
	@Override
    public boolean equals(final Object obj) {
        if(obj instanceof Product == false) {
            return false;
        }
        
        if(this == obj) {
            return true;
        }
        
        final Product product = (Product)obj;
        return new EqualsBuilder()
            .append(name, product.getName())
            .append(price, product.getPrice())
            .append(description, product.getDescription())
            .append(quantity, product.getQuantity())
            .isEquals();
    }
	
	@Override
    public String toString() {
        return new ToStringBuilder(this)
        .append("name" , name)
        .append("price", price)
        .append("description", description)
        .append("quantity", quantity)
        .toString();
    }
}
