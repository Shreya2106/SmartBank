package com.pack.bank.model;

public class Account {
	private String accountName;
	private String branch;
	private String currency;
	private String balance;
	private String openingBalance;
	private String closingBalance;
	private int accountNumber;
	private int corporateId;
	private int active;
	
	public int getCorporateId() {
		return this.corporateId;
	}
	public void setCorporateId(int corporateId) {
		this.corporateId=corporateId;
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
	public Account(int corporateId,String accountName,String branch,String currency,String balance,String openingBalance,String closingBalance) {
		this.accountName = accountName;
		this.branch=branch;
		this.currency=currency;
		this.balance=balance;
		this.openingBalance=openingBalance;
		this.closingBalance=closingBalance;
		this.corporateId=corporateId;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
}
