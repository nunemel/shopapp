package com.egs.shopapp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * A Category is an entity to classify Products.
 * Categories can have childCategories, but a given category has a single parent (optional).
 *
 * @author Nune Melikyan
 */
@Entity
@Table(name = "e_category")
@EntityListeners(AuditingEntityListener.class)
public class Category extends Persistent<String> {

	@Transient
	private static final long serialVersionUID = -7558746733334368143L;
	
	@Column(name = "name", nullable = false)
    private String name;
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Column
    private String description; 
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;
    
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private Set<Product> products;
    
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @OneToMany(mappedBy = "parent")
    private Set<Category> childCategories;

    public Set<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set<Category> childCategories) {
        this.childCategories = childCategories;
    }
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="imageId", nullable = true)
	private Image image; 
	
    public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
   
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
	        .append(name)
	        .append(description)
	        .toHashCode();
    }
	
	@Override
    public boolean equals(final Object obj) {
        if(obj instanceof Category == false) {
            return false;
        }
        if(this == obj) {
            return true;
        }
        final Category category = (Category)obj;
        return new EqualsBuilder()
            .append(name, category.getName())
            .append(description, category.getDescription())
            .isEquals();
    }
	
	@Override
    public String toString() {
        return new ToStringBuilder(this)
        .append("name" , name)
        .append("description" , description)
        .toString();
    }
}
