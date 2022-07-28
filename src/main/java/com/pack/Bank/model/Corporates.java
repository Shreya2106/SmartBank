package com.pack.Bank.model;

public class Corporates {
	private String corporateName, address, contactNumber;
	private int corporateId,softdeleted;

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
	
}
