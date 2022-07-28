package com.pack.Bank.model;

public class Transaction {
	private String accountNumber,transactionDate,transactionType,balance;
	private int transactionId;
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountNumber() {
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
	
}
