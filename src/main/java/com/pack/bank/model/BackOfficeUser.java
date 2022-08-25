package com.pack.bank.model;

public class BackOfficeUser {
	private String loginId;
	private String password;
	private String username;

	public BackOfficeUser(String loginId,String password,String username) {
		this.loginId = loginId;
		this.password = password;
		this.username = username;
	}
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}
