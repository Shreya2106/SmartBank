package com.pack.bank.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Corporates {
	
	@NotEmpty(message="Corporate Name cannot be empty")
	private String corporateName;
	
	@Address(message="Address is not valid")
	private String address;
	
	@Contact(message="Contact Number is not valid. It must begin with either 6/7/8/9 and contain 10 digits.")
	private String contactNumber;
	private int corporateId;
	private int softdeleted;

	public int getSoftdeleted() {
		return softdeleted;
	}

	public void setSoftdeleted(int softdeleted) {
		this.softdeleted = softdeleted;
	}

	public int getCorporateId() {
		return corporateId;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setCorporateId(int corporateId) {
		this.corporateId = corporateId;
		
	}
	
	public Corporates(String corporateName, String address,String contactNumber) {
		this.corporateName = corporateName;
		this.address = address;
		this.contactNumber = contactNumber;
	}
	public Corporates() {
		
	}
	
}
