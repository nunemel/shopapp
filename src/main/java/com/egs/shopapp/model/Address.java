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
 * Address object of the user.
 *  
 * @author Nune
 *
 */
@Entity
@Table(name = "e_address")
@EntityListeners(AuditingEntityListener.class)
public class Address extends Persistent<String> {

	@Transient
	private static final long serialVersionUID = -53438323596814743L;

	private String street;

	@Column(name = "Street", nullable = true, length = 255)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	private String city;

	@Column(name = "City", nullable = true, length = 32)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	private String zip;

	@Column(name = "Zip", nullable = true, length = 32)
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	private String country;

	@Column(name = "Country", nullable = true, length = 32)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	private String phone;

	@Column(name = "Phone", nullable = true, length = 32)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String fax;

	@Column(name = "Fax", nullable = true, length = 32)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
		
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(street)
				.append(city)
				.append(zip)
				.append(country)
				.append(phone)
				.append(fax)
				.toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Address == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final Address address = (Address) obj;
		return new EqualsBuilder()
				.append(street, address.getStreet())
				.append(city, address.getCity())
				.append(zip, address.getZip())
				.append(country, address.getCountry())
				.append(phone, address.getPhone())
				.append(fax, address.getFax()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("street", street != null ? street : "")
				.append("city", city != null ? city : "")
				.append("zip", zip != null ? zip : "")
				.append("country", country != null ? country : "")
				.append("phone", phone != null ? phone : "")
				.append("fax", fax != null ? fax : "").toString();
	}
}
