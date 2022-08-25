package com.pack.bank;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.pack.bank.dao.BackOfficeUserDao;
import com.pack.bank.model.BackOfficeUser;

class BackOfficeUserDaoTests {

	
	BackOfficeUserDao bdao;
	
	
	BackOfficeUser buser;
	
	@BeforeEach
	void init() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("config-test.xml");
		bdao = context.getBean("backOfficeUserDao", BackOfficeUserDao.class);
		((ClassPathXmlApplicationContext) context).close();
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
