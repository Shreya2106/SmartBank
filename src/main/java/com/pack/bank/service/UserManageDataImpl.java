package com.pack.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.model.User;
import com.pack.bank.repository.CorpoartesRepository;
import com.pack.bank.repository.UserRepository;

@Service("userManageDataImpl")
public class UserManageDataImpl implements ManageData<User,String>{

	@Autowired
	UserRepository uRepo;
	
	@Autowired
	CorpoartesRepository cRepo;
	
	public int edit(User data) {
		String add = data.getAddress();
		String ph = data.getContactNumber();
		String email = data.getLoginId();
		return uRepo.updateCustom(add,ph,email);
	}

	@Override
	public int delete(String id) {
		 return uRepo.deleteByEmail(id);
	}

	@Override
	public void add(User data) {
		 uRepo.save(data);
	}

	@Override
	public List<User> viewAll(){
		return uRepo.findAllCustom();
	}
	
	public User getUserByEmail(String ide) {
		return uRepo.getUserByEmail(ide);
	}

	public int changePass(String email, String password1) {
		return uRepo.changePass(email, password1);
	}

}
