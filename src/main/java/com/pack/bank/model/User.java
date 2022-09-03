package com.pack.bank.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Id;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="role",columnDefinition = "varchar(255) default \"USER\"")
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Column(name="loginId")
	@NotEmpty(message="LoginId cannot be empty") 
	private String loginId;
	
	@Column(name="password", columnDefinition = "varchar(255) default \"userC123#\"")
	private String password;
	
	@Column(name="username")
	@NotEmpty(message="Username cannot be empty") 
	private String username;
	private String department;
	
	@Column(name="address")
	@Address(message="Address is not valid")
	private String address;
	
	@Column(name="contactNumber")
	@Contact(message="Contact Number is not valid. It must begin with either 6/7/8/9 and contain 10 digits.")
	private String contactNumber;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "corporateId", referencedColumnName = "corporateId")
	private Corporates corporatemap1;
	
	@Column(name = "changed", columnDefinition = "boolean default false")
	private boolean changed;
	
	public boolean getChanged() {
		return changed;
	}
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	public String getAddress() {
		return this.address;
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
	public Corporates getCorporate() {
		return this.corporatemap1;
	}
	public void setCorporate(Corporates corporate) {
		this.corporatemap1=corporate;
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
	public User(Corporates corpoarte,String loginId,String password,String username,String role,String department,String address,String contactNumber) {
		this.corporatemap1 = corpoarte;
		this.loginId = loginId;
		this.password = password;
		this.role = role;
		this.username = username;
		this.department = department;
		this.address = address;
		this.contactNumber = contactNumber;
		
	}
}
