package com.pack.bank.model;

public class Transaction {
	private String transactionDate;
	private String transactionType;
	private String balance;
	private int transactionId;
	private int accountNumber;
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getAccountNumber() {
		return this.accountNumber;
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
	public Transaction(int accountNumber,String transactionType,String transactionDate,String balance) {
		this.accountNumber = accountNumber;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.balance = balance;
	}
	
}
