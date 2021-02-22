package com.egs.shopapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 
 * The Review object for adding reviews to product.
 * 
 * @author Nune
 *
 */
@Entity
@Table(name = "e_review")
@EntityListeners(AuditingEntityListener.class)
public class Review extends Persistent<String> {
	
	@Transient
	private static final long serialVersionUID = -3907728760892580510L;
		
	@ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
		
	@Column(name = "rating", nullable = true)
	private Integer rating;
	
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	@Column(name = "comment", nullable = true)
	private String comment;
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
		
	@Override
    public int hashCode() {
        return new HashCodeBuilder()
	        .append(rating)
	        .append(comment)
	        .append(product == null ? null : product.getId())
	        .toHashCode();
    }
	
	@Override
    public boolean equals(final Object obj) {
        if(obj instanceof Review == false) {
            return false;
        }
        if(this == obj) {
            return true;
        }
        final Review review = (Review)obj;
        return new EqualsBuilder()
            .append(rating, review.getRating())
            .append(comment, review.getComment())
            .append(product == null ? null : product.getId(), 
            		product == null ? null : product.getId())
            .isEquals();
    }
	
	@Override
    public String toString() {
        return new ToStringBuilder(this)
        .append("rating" , rating)
        .append("comment", comment)
        .append(product == null ? null : product.getId())
        .toString();
    }
}	