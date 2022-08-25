package com.pack.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.dao.BackOfficeUserDao;

@Service("adminLoginService")
public class AdminLoginServiceImpl implements LoginService{
	
	@Autowired
	BackOfficeUserDao adao;
	
	@Override
	public boolean validateUser(String email,String password) {
		return (adao.validateUser(email,password));
	}
	public String getUsername(String email) {
		return adao.getUsername(email);
	}

}
