package com.pack.bank.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.controllers.LoginException;
import com.pack.bank.controllers.NoSuchUserException;
import com.pack.bank.model.BackOfficeUser;
import com.pack.bank.repository.BackOfficeUserRepository;

@Service("adminLoginService")
public class AdminLoginServiceImpl implements LoginService{
	
	@Autowired
	BackOfficeUserRepository buserRepository;
	
	@Override
	public boolean validateUser(String email,String password) {
		BackOfficeUser buser = buserRepository.getUserByEmail(email);
		if(buser != null) {
			if((buser.getPassword()).equals(password)) {
				return true;
			}
			else {
				throw new LoginException("Invalid Credentials");
			}
				
		}
		throw new NoSuchUserException("No such user");
	}
	public String getUsername(String email) {
		return buserRepository.getUserName(email);
	}
	
	public void insertIfEmpty() {
		if(buserRepository.checkEmpty()==0) {
			BackOfficeUser buser  = new BackOfficeUser("alanwalker@smartbank.in","#AlanWalker123","Alan");
			buserRepository.save(buser);
		}
	}

}
