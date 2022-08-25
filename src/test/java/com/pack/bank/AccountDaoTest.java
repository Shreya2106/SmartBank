package com.pack.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.pack.bank.dao.AccountDao;
import com.pack.bank.dao.CorporateDao;
import com.pack.bank.model.Account;
import com.pack.bank.model.Corporates;


class AccountDaoTest {

	AccountDao adao;
	
	
	CorporateDao cdao;
	
	Account a;
	
	@BeforeEach
	@Transactional
	@Rollback(true)
	void init() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("config-test.xml");
		adao = context.getBean("accountDao", AccountDao.class);
		cdao = context.getBean("corporateDao", CorporateDao.class);
		((ClassPathXmlApplicationContext) context).close();
		List<Corporates> cList= cdao.viewAll();
		int id= cList.get(cList.size()-1).getCorporateId();
		a = new Account(id,"Account 10","Mumbai,India","Indian Rupee","500000","50000","500000");
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
