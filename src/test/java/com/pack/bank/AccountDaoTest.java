package com.pack.bank;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.pack.bank.dao.AccountDao;
import com.pack.bank.dao.CorporateDao;
import com.pack.bank.model.Account;
import com.pack.bank.model.Corporates;

@SpringBootTest
class AccountDaoTest {

	@Autowired
	AccountDao adao;
	
	@Autowired
	CorporateDao cdao;
	
	Account a;
	
	@BeforeEach
	void setUp() throws Exception{
		List<Corporates> cList= cdao.viewAll();
		int id= cList.get(cList.size()-1).getCorporateId();
		a = new Account(id,"Account 10","Mumbai,India","Indian Rupee","500000","50000","500000");
	}
	
	@AfterEach
	void tearDown() throws Exception{
		a = null;
	}
	
	@Test
    @Transactional
    @Rollback(true)
    void accInsertTest() {
		int j=adao.insert(a);
		assertNotEquals(0,j);
    }

	
	@Test
	@Transactional
	@Rollback(true)
	void accDeleteTest() {
		List<Account> aList= adao.viewAll();
		int id= aList.get(aList.size()-1).getAccountNumber();
		int j= adao.delete(id);
		assertNotEquals(0,j); 
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void accSetBalTransTest() {
		List<Account> aList= adao.viewAll();
		int id= aList.get(aList.size()-1).getAccountNumber();
		int j= adao.setBalanceAfterTrans("50000",id);
		assertNotEquals(0,j); 
	}
	
}
