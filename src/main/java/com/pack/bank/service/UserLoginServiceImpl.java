package com.pack.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.model.User;
import com.pack.bank.repository.UserRepository;

@Service("userLoginService")
public class UserLoginServiceImpl implements LoginService{
	
	@Autowired
	UserRepository uRepository;
	
	@Override
	public String getUsername(String email) {
		return uRepository.getUserName(email);
	}

	public boolean getStatus(String email) {
		return uRepository.getStatus(email);
	}

	@Override
	public User getUser(String email) {
		return uRepository.getUserByEmail(email);
	}
}
