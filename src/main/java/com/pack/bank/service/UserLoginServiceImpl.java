package com.pack.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.dao.UserDao;

@Service("userLoginService")
public class UserLoginServiceImpl implements LoginService{
	
	@Autowired
	UserDao udao;
	
	@Override
	public boolean validateUser(String email,String password) {
		return (udao.validateUser(email,password));
	}

	public String getUsername(String email) {
		return udao.getUsername(email);
	}

	public int getStatus(String email) {
		return udao.getStatus(email);
	}

}
