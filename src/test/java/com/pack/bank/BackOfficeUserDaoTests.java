package com.pack.bank;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pack.bank.dao.BackOfficeUserDao;
import com.pack.bank.model.BackOfficeUser;

@SpringBootTest
class BackOfficeUserDaoTests {

	@Autowired
	BackOfficeUserDao bdao;
	
	
	BackOfficeUser buser;
	
	@BeforeEach
	void setUp() throws Exception{
		buser = new BackOfficeUser("alanwalker@smartbank.in", "#AlanWalker123","Alan");
	}
	
	@AfterEach
	void tearDown() throws Exception{
		buser = null;
	}

	@Test()
	void checkValiditUserTest() throws Exception{
		String email = buser.getLoginId();
		String pass = buser.getPassword();
		Boolean j = bdao.validateUser(email,pass);
		assertEquals(true,j); 
	}
	
	@Test()
	void checkGetUserNameTest() throws Exception{
		String email = buser.getLoginId();
		String j = bdao.getUsername(email);
		assertEquals("Alan",j); 
	}
 
}
