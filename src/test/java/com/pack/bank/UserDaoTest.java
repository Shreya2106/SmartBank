package com.pack.bank;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.pack.bank.controllers.NoSuchUserException;
import com.pack.bank.dao.CorporateDao;
import com.pack.bank.dao.UserDao;
import com.pack.bank.model.Corporates;
import com.pack.bank.model.User;

@SpringBootTest
class UserDaoTest {

	@Autowired
	UserDao udao;
	
	@Autowired
	CorporateDao cdao;
	
	User u;
	
	@Rule
	  public final ExpectedException exception = ExpectedException.none();
	
	@BeforeEach
	void setUp() throws Exception{
		List<Corporates> cList= cdao.viewAll();
		int id= cList.get(cList.size()-1).getCorporateId();
		u = new User(id,"max@abc.in","userC123##","Max","CSE","Pune,India","7896541230");
	}
	
	@AfterEach
	void tearDown() throws Exception{
		u = null;
	}
	
	@Test()
	void checkValiditUserTest() throws Exception{
		String email = u.getLoginId();
		String pass = u.getPassword();
		Exception thrown = assertThrows(NoSuchUserException.class,
		           () -> { udao.validateUser(email,pass); });

		   assertTrue(thrown.getMessage().contains("No such user"));
	}

	@Test
    @Transactional
    @Rollback(true)
    void corpInsertTest() {
		int j=udao.insert(u);
		assertNotEquals(0,j);
    }

	@Test
	@Transactional
	@Rollback(true)
	void userModifyTest() {
		List<User> uList= udao.viewAll();
		String id= uList.get(uList.size()-1).getLoginId();
		User u1=udao.getUserById(id);
		u1.setAddress("newAddress");
		int j= udao.modify(u1);
		assertNotEquals(0,j); 
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void userDeleteTest() {
		List<User> uList= udao.viewAll();
		String id= uList.get(uList.size()-1).getLoginId();
		int j= udao.delete(id);
		assertNotEquals(0,j); 
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void userChangePassTest() {
		List<User> uList= udao.viewAll();
		String id= uList.get(uList.size()-1).getLoginId();
		int j= udao.changePass(id,"userC123##");
		assertNotEquals(0,j); 
	}
}
