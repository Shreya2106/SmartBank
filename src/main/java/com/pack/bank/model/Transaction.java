package com.pack.bank.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Id;

@Entity
@Table(name = "transaction")
public class Transaction{
	
	@NotEmpty(message="Date cannot be empty") 
	@Column(name="transactionDate")
	private String transactionDate;
	
	@Column(name="transactionType")
	private String transactionType;
	
	@NotEmpty(message="Balance cannot be empty") 
	@Column(name="balance")
	private String balance;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq3")
	@SequenceGenerator(name="seq3", initialValue=1001)
	@Column(name="transactionId")
	private int transactionId;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "accountNumber", referencedColumnName = "accountNumber")
	private Account account;
	
	
	public void setAccount(Account account) {
		this.account=account;
	}
	public Account getAccount() {
		return this.account;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getTransactionId() {
		return this.transactionId;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionType() {
		return this.transactionType;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionDate() {
		return this.transactionDate;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getBalance() {
		return this.balance;
	}
	public Transaction(){
		
	}
	public Transaction(Account account,String transactionType,String transactionDate,String balance) {
		this.account = account;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.balance = balance;
	}
	
}
