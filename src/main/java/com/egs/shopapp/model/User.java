package com.egs.shopapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;

/**
 * 
 * The User object.
 * 
 * @author Nune
 *
 */
@Entity
@Table(name = "e_user")
@EntityListeners(AuditingEntityListener.class)
public class User extends Persistent<String> {
		
	@Transient
	private static final long serialVersionUID = -1775152133969032340L;

	@NotNull
	@Column(name = "first_name", nullable = false, length = 64)
	private String firstName;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "last_name", nullable = false, length = 64)
	private String lastName;
       
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(unique = true)
	private String username;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@NotBlank(message = "password is mandatory")
	@Column(nullable = false)
	private String password;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotBlank(message = "email is mandatory")
	@Column(length = 100, unique = true, nullable = false)
	private String email;
		
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Type(type="true_false")
	@Column(name = "Active", length = 1, nullable = false)
	private Boolean active = true;
	
	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, 
			CascadeType.DETACH, 
			CascadeType.MERGE, 
			CascadeType.REFRESH})
    @JoinTable(name="e_user_role",
        joinColumns=
            @JoinColumn(name="user_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="role_id", referencedColumnName="id")
        )
	private Set<Role> roles = new HashSet<Role>();


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	} 
	
    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }
 
    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
	
    @OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="address_id", nullable = true)
	private Set<Address> addresses;
    
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
		
	@Override
    public int hashCode() {
        return new HashCodeBuilder()
    	    .append(firstName)
    	    .append(lastName)
	        .append(username)
	        .append(password)
	        .append(email)
	        .append(active)
	        .toHashCode();
    }
	
	@Override
    public boolean equals(final Object obj) {
        if(obj instanceof User == false) {
            return false;
        }
        if(this == obj) {
            return true;
        }
        final User user = (User)obj;
        return new EqualsBuilder()
        	.append(firstName, user.getFirstName())
            .append(lastName, user.getLastName())
            .append(username, user.getUsername())
            .append(password, user.getPassword()) 
            .append(email, user.getEmail())
            .append(active, user.isActive())
            .isEquals();
    }
	
	@Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("firstName")
            .append("lastName")
	        .append("username" , username)
	        .append("password", password)
	        .append("email")
	        .append("isActive", active)
	        .toString();
    }
}
