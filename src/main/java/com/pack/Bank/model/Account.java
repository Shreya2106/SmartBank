package com.pack.Bank.model;

public class Account {
	private String accountNumber,accountName,branch,currency,balance,openingBalance,closingBalance,corporateName;
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	private int corporateId;
	
	public int getCorporateId() {
		return this.corporateId;
	}
	public void setCorporateId(int corporateId) {
		this.corporateId=corporateId;
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
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
}
