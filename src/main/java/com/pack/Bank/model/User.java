package com.pack.Bank.model;

public class User {
	private String loginId,password,username,department,address,contactNumber;
	private int corporateId,changed;
	
	public int getChanged() {
		return changed;
	}
	public void setChanged(int changed) {
		this.changed = changed;
	}
	public String getAddress() {
		return this.address;
	}
	public int getCorporateId() {
		return this.corporateId;
	}
	public String getLoginId() {
		return this.loginId;
	}
	public String getPassword() {
		return this.password;
	}
	public String getUserName() {
		return this.username;
	}
	public String getDepartment() {
		return this.department;
	}
	public String getContactNumber() {
		return this.contactNumber;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCorporateId(int corporateId) {
		this.corporateId=corporateId;
	}
	public void setLoginId(String loginId) {
		this.loginId=loginId;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public void setUserName(String username) {
		this.username=username;
	}
	public void setDepartment(String department) {
		this.department=department;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber=contactNumber;
	}
}
