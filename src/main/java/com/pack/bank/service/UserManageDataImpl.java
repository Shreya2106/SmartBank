package com.pack.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.dao.UserDao;
import com.pack.bank.model.User;

@Service("userManageDataImpl")
public class UserManageDataImpl implements ManageData<User,String>{

	@Autowired
	UserDao udao;
	
	public int edit(User data) {
		return udao.modify(data);
	}

	@Override
	public int delete(String id) {
		return udao.delete(id);
	}

	@Override
	public int add(User data) {
		return udao.insert(data);
	}

	@Override
	public List<User> viewAll(){
		return udao.viewAll();
	}
	
	public User getUserById(String ide) {
		return udao.getUserById(ide);
	}

	public int changePass(String email, String password1) {
		return udao.changePass(email, password1);
	}

}
