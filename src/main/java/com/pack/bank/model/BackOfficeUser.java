package com.pack.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BackOfficeUser")
public class BackOfficeUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="loginId")
	private String loginId;
	
	@Column(name="password")
	private String password;
	
	@Column(name="username")
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
	public BackOfficeUser() {
		
	}
	

}
