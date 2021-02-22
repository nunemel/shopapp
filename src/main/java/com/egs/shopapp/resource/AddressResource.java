package com.egs.shopapp.resource;

import org.springframework.hateoas.ResourceSupport;

public class AddressResource extends ResourceSupport {

	private final String street;
	private final String city;
	private final String country;
	private final String zip;
	private final String phone;
	private final String fax;
	
	public AddressResource(String street, String city, String country, String zip, String phone, String fax) {
		super();
		this.street = street;
		this.city = city;
		this.country = country;
		this.zip = zip;
		this.phone = phone;
		this.fax = fax;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getZip() {
		return zip;
	}

	public String getPhone() {
		return phone;
	}

	public String getFax() {
		return fax;
	}
}
