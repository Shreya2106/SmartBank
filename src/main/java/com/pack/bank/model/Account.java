package com.pack.bank.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Id;

@Entity
@Table(name = "Account")
public class Account {
	
	@Column(name = "accountName")
	private String accountName;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "balance")
	private String balance;
	
	@Column(name = "openingBalance")
	private String openingBalance;
	
	@Column(name = "closingBalance")
	private String closingBalance;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq1")
	@SequenceGenerator(name="seq1", initialValue=1000010000)
	@Column(name = "accountNumber")
	private int accountNumber;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "account")
	 private Collection<Transaction> transaction;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "corporateId", referencedColumnName = "corporateId")
	private Corporates corporatemap;
	
	@Column(name = "active", columnDefinition = "boolean default true")
	private int active;
	
	public Corporates getCorporate() {
		return this.corporatemap;
	}
	public void setCorporate(Corporates corporate) {
		this.corporatemap=corporate;
	}
	
	public int getAccountNumber() {
		return this.accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber=accountNumber;
	}
	
	public String getAccountName() {
		return this.accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName=accountName;
	}
	
	public String getBranch() {
		return this.branch;
	}
	public void setBranch(String branch) {
		this.branch=branch;
	}
	
	public String getCurrency() {
		return this.currency;
	}
	public void setCurrency(String currency) {
		this.currency=currency;
	}
	
	public String getBalance() {
		return this.balance;
	}
	public void setBalance(String balance) {
		this.balance=balance;
	}
	
	public String getOpeningBalance() {
		return this.openingBalance;
	}
	public void setOpeningBalance(String openingBalance) {
		this.openingBalance=openingBalance;
	}
	
	public String getClosingBalance() {
		return this.closingBalance;
	}
	public void setClosingBalance(String closingBalance) {
		this.closingBalance=closingBalance;
	}
	
	public Account() {
		
	}
	public Account(Corporates corporate,String accountName,String branch,String currency,String balance,String openingBalance,String closingBalance) {
		this.accountName = accountName;
		this.branch=branch;
		this.currency=currency;
		this.balance=balance;
		this.openingBalance=openingBalance;
		this.closingBalance=closingBalance;
		this.corporatemap=corporate;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
}
