package com.pack.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.model.BackOfficeUser;
import com.pack.bank.repository.BackOfficeUserRepository;

@Service("adminLoginService")
public class AdminLoginServiceImpl implements LoginService{
	
	@Autowired
	BackOfficeUserRepository buserRepository;
	
	@Override
	public String getUsername(String email) {
		return buserRepository.getUserName(email);
	}
	
	public void insertIfEmpty() {
		if(buserRepository.checkEmpty()==0) {
			BackOfficeUser buser  = new BackOfficeUser("alanwalker@smartbank.in","#AlanWalker123","Alan","ROLE_ADMIN");
			buserRepository.save(buser);
		}
	}
	
	@Override
	public BackOfficeUser getUser(String email) {
		return buserRepository.getUserByEmail(email);
	}

}
