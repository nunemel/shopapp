package com.egs.shopapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Image object for product.
 * 
 * @author Nune
 *
 */
@Entity
@Table(name = "e_image")
@EntityListeners(AuditingEntityListener.class)
public class Image extends Persistent<String> {
	
	@Transient
	private static final long serialVersionUID = -8684258861552705818L;
	
	@Column(nullable = false)
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "path", nullable = false)
	private String path;
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "description", nullable = true)
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToOne(mappedBy = "image", cascade = CascadeType.REFRESH)
	private Category category;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
    public int hashCode() {
        return new HashCodeBuilder()
	        .append(name)
	        .append(path)
	        .append(description)
	        .append(product == null ? null : product.getId())
	        .toHashCode();
    }
	
	@Override
    public boolean equals(final Object obj) {
		
        if(obj instanceof Image == false) {
            return false;
        }
        
        if(this == obj) {
            return true;
        }
        
        final Image image = (Image)obj;
        return new EqualsBuilder()
            .append(name, image.getName())
            .append(path, image.getPath())
            .append(description, image.getDescription())
            .append(product == null ? null : product.getId(), 
            		product == null ? null : product.getId())
            .isEquals();
    }
	
	@Override
    public String toString() {
        return new ToStringBuilder(this)
        		.append(name)
        		.append(path)
        		.append(description)
        		.append(product == null ? null : product.getId())
        		.toString();
    }
}
