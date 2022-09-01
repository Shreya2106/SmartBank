package com.pack.bank;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import com.pack.bank.model.Account;
import com.pack.bank.model.Corporates;
import com.pack.bank.repository.AccountRepository;
import com.pack.bank.repository.CorpoartesRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class AccountDaoTest {

	@Autowired
	AccountRepository aRepo;
	
	@Autowired
	CorpoartesRepository cRepo;
	
	Account a;
	
	@BeforeEach
	void setUp() throws Exception{
		List<Corporates> cList= cRepo.findAll();
		Corporates c = cList.get(cList.size()-1);
		a = new Account(c,"Account 10","Mumbai,India","Indian Rupee","500000","50000","500000");
	}
	
	@AfterEach
	void tearDown() throws Exception{
		a = null;
	}
	
	@Test
    @Transactional
    @Rollback(true)
	@Order(1)
    void accInsertTest() {
		Account j=aRepo.save(a);
		assertNotEquals(null,j);
    }

	
	@Test
	@Transactional
	@Rollback(true)
	@Order(3)
	void accDeleteTest() {
		List<Account> aList= aRepo.findAll();
		int id= aList.get(aList.size()-1).getAccountNumber();
		int j= aRepo.deleteCustom(id);
		assertNotEquals(0,j); 
	}
	
	@Test
	@Transactional
	@Rollback(true)
	@Order(2)
	void accSetBalTransTest() {
		List<Account> aList= aRepo.findAll();
		int id= aList.get(aList.size()-1).getAccountNumber();
		int j= aRepo.setBalanceAfterTrans("50000",id);
		assertNotEquals(0,j); 
	}
	
}
