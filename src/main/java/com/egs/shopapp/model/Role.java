package com.egs.shopapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Role object for user roles.
 * 
 * @author Nune
 *
 */
@Entity
@Table(name = "e_role")
@EntityListeners(AuditingEntityListener.class)
public class Role extends Persistent<String> {
	
	@Transient 
	private static final long serialVersionUID = 3744611208927983741L;
	
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
	
	@ManyToMany(mappedBy = "roles", cascade = {CascadeType.PERSIST, 
			CascadeType.DETACH, 
			CascadeType.MERGE, 
			CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<User>();
		
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		this.users.add(user);
		user.getRoles().add(this);
	}

	public void removeUser(User user) {
		this.users.remove(user);
		user.getRoles().remove(this);
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
        if(obj instanceof Role == false) {
            return false;
        }
        if(this == obj) {
            return true;
        }
        final Role role = (Role)obj;
        return new EqualsBuilder()
            .append(name, role.getName())
            .append(description, role.getDescription())
            .isEquals();
    }
	
	@Override
    public String toString() {
        return new ToStringBuilder(this)
        .append("name" , name)
        .append("description", description)
        .toString();
    }
}
