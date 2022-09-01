package com.pack.bank.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table(name = "Corporates")
public class Corporates {
	
	@Column(name="corporateName")
	@NotEmpty(message="Corporate Name cannot be empty")
	private String corporateName;
	
	@Column(name="address")
	@Address(message="Address is not valid")
	private String address;
	
	@Column(name="contactNumber")
	@Contact(message="Contact Number is not valid. It must begin with either 6/7/8/9 and contain 10 digits.")
	private String contactNumber;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq2")
	@SequenceGenerator(name="seq2", initialValue=1001)
	@Column(name="corporateId")
	private int corporateId;
	
	@Column(name="softdeleted", columnDefinition = "boolean default false")
	private int softdeleted;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "corporatemap")
	 private Collection<Account> account;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "corporatemap1")
	 private Collection<User> user;
	
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
