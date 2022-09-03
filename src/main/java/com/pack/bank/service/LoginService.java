package com.pack.bank.service;


public interface LoginService<T> {
	String getUsername(String email);
	T getUser(String email);
}
