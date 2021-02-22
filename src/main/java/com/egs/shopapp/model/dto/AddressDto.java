package com.egs.shopapp.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.egs.shopapp.model.Address;

/**
 * Address DTO.
 * 
 * @author Nune
 *
 */
@Validated
public class AddressDto {
	
	@NotEmpty(message = "country is empty")
	@NotNull(message = "country is null")
	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@NotEmpty(message = "city is empty")
	@NotNull(message = "city is null")
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@NotEmpty(message = "street is empty")
	@NotNull(message = "street is null")
	private String street;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@NotEmpty(message = "zip is empty")
	@NotNull(message = "zip is null")
	private String zip;

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@NotEmpty(message = "phone is empty")
	@NotNull(message = "phone is null")
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String fax;

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	/**
     * 
     * @return Address object from DTO.
     */
	public Address getCreateAddressFromDto(){
    	
		Address address = new Address();
        
		address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setZip(zip);
        address.setPhone(phone);
        address.setFax(fax);
                    
        return address;
    }
	
	/**
     * 
     * @return Address object from DTO.
     */
	public Address getUpdateAddressFromDto(Address address){
    	
		address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setZip(zip);
        address.setPhone(phone);
        address.setFax(fax);
                    
        return address;
    }
}
