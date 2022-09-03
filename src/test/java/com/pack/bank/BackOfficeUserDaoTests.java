package com.pack.bank;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.pack.bank.model.BackOfficeUser;
import com.pack.bank.repository.BackOfficeUserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class BackOfficeUserDaoTests {

	@Autowired
	BackOfficeUserRepository bRepo;
	
	
	BackOfficeUser buser;
	
	@BeforeEach
	void setUp() throws Exception{
		buser = new BackOfficeUser("alanwalker@smartbank.in", "#AlanWalker123","Alan","ROLE_ADMIN");
	}
	
	@AfterEach
	void tearDown() throws Exception{
		buser = null;
	}


	@Test()
	void checkGetUserNameTest() throws Exception{
		String email = buser.getLoginId();
		String j = bRepo.getUserName(email);
		assertEquals("Alan",j); 
	}
 
}
