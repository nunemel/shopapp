package com.egs.shopapp.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Persistent super class for all domain objects.
 * 
 * @author Nune
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Persistent<U>  implements Serializable  {
   
	@Transient
	private static final long serialVersionUID = -4510926390250725012L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @CreatedBy
    protected U createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate;

    @LastModifiedBy
    protected U lastModifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastModifiedDate;
  
	public Date getCreationDate() {
		return creationDate;
	}

	public U getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(U createdBy) {
		this.createdBy = createdBy;
	}

	public U getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(U lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(creationDate)
            .append(createdBy)
            .append(lastModifiedDate)
            .append(lastModifiedBy)
            .toHashCode();
    }

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Persistent == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		
		@SuppressWarnings("unchecked") // it is Persistent object no need for check
		final Persistent<U> prs = (Persistent<U>) obj;
		return new EqualsBuilder()
				.append(this.id, prs.getId())
				.append(creationDate, prs.getCreationDate())
				.append(createdBy, prs.getCreatedBy())
				.append(lastModifiedDate,prs.getLastModifiedDate())
				.append(lastModifiedBy,prs.getLastModifiedBy())
				.isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(this.id)
				.append("createdBy", createdBy)
				.append("creationDate", creationDate)
				.append("lastModifiedBy", lastModifiedBy)
				.append("lastModifiedDate", lastModifiedDate).toString();
	}
	
}
