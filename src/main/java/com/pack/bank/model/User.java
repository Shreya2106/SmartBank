package com.pack.bank.model;

import org.hibernate.validator.constraints.NotEmpty;

public class User {
	
	@NotEmpty(message="LoginId cannot be empty") 
	private String loginId;
	
	private String password;
	
	@NotEmpty(message="Username cannot be empty") 
	private String username;
	private String department;
	
	@Address(message="Address is not valid")
	private String address;
	
	@Contact(message="Contact Number is not valid. It must begin with either 6/7/8/9 and contain 10 digits.")
	private String contactNumber;
	private int corporateId;
	private int changed;
	
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
	public String getUsername() {
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
	public void setUsername(String username) {
		this.username=username;
	}
	public void setDepartment(String department) {
		this.department=department;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber=contactNumber;
	}
	public User() {
		
	}
	public User(int corpoarteId,String loginId,String password,String username,String department,String address,String contactNumber) {
		this.corporateId = corpoarteId;
		this.loginId = loginId;
		this.password = password;
		this.username = username;
		this.department = department;
		this.address = address;
		this.contactNumber = contactNumber;
		
	}
}
