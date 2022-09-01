package com.pack.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.controllers.LoginException;
import com.pack.bank.controllers.NoSuchUserException;
import com.pack.bank.model.User;
import com.pack.bank.repository.UserRepository;

@Service("userLoginService")
public class UserLoginServiceImpl implements LoginService{
	
	@Autowired
	UserRepository uRepository;
	
	@Override
	public boolean validateUser(String email,String password) {
		User user = uRepository.getUserByEmail(email);
		if(user != null) {
			if((user.getPassword()).equals(password)) {
				return true;
			}
			else {
				throw new LoginException("Invalid Credentials");
			}
				
		}
		throw new NoSuchUserException("No such user");
	}
	public String getUsername(String email) {
		return uRepository.getUserName(email);
	}

	public boolean getStatus(String email) {
		return uRepository.getStatus(email);
	}

}
