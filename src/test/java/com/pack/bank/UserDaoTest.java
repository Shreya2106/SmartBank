package com.pack.bank;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.pack.bank.model.Corporates;
import com.pack.bank.model.User;
import com.pack.bank.repository.CorpoartesRepository;
import com.pack.bank.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class UserDaoTest {

	@Autowired
	UserRepository uRepo;
	
	@Autowired
	CorpoartesRepository cdao;
	
	User u;
	
	@Rule
	  public final ExpectedException exception = ExpectedException.none();
	
	@BeforeEach
	void setUp() throws Exception{
		List<Corporates> cList= cdao.findAll();
		Corporates c = cList.get(cList.size()-1);
		u = new User(c,"max@abc.in","userC123##","ROLE_USER","Max","CSE","Pune,India","7896541230");
	}
	
	@AfterEach
	void tearDown() throws Exception{
		u = null;
	}
	
	@Test
    @Transactional
    @Rollback(true)
	@Order(1)
    void corpInsertTest() {
		User j=uRepo.save(u);
		assertNotEquals(null,j);
    }

	@Test
	@Transactional
	@Rollback(true)
	@Order(2)
	void userModifyTest() {
		List<User> uList= uRepo.findAll();
		String id= uList.get(uList.size()-1).getLoginId();
		User u1=uRepo.getUserByEmail(id);
		u1.setAddress("newAddress");
		User j= uRepo.save(u1);
		assertNotEquals(null,j); 
	}
	
	@Test
	@Transactional
	@Rollback(true)
	@Order(4)
	void userDeleteTest() {
		List<User> uList= uRepo.findAll();
		String id= uList.get(uList.size()-1).getLoginId();
		int j= uRepo.deleteByEmail(id);
		assertNotEquals(0,j); 
	}
	
	@Test
	@Transactional
	@Rollback(true)
	@Order(3)
	void userChangePassTest() {
		List<User> uList= uRepo.findAll();
		String id= uList.get(uList.size()-1).getLoginId();
		int j= uRepo.changePass(id,"userC123##");
		assertNotEquals(0,j); 
	}
}
