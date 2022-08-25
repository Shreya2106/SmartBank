package com.pack.bank;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.pack.bank.dao.AccountDao;
import com.pack.bank.dao.TransDao;
import com.pack.bank.model.Account;
import com.pack.bank.model.Transaction;

class TransactionDaoTest {
	
	
	TransDao tdao;
	
	AccountDao adao;

	Transaction t;
	
	@BeforeEach
	@Transactional
	@Rollback(true)
	void init() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("config-test.xml");
		tdao = context.getBean("transDao", TransDao.class);
		adao = context.getBean("accountDao", AccountDao.class);
		((ClassPathXmlApplicationContext) context).close();
		List<Account> aList= adao.viewAll();
		int accno = aList.get(aList.size()-1).getAccountNumber();
		t = new Transaction(accno,"Debit","2022-08-08","600");
	}
	
	@Test
    @Transactional
    @Rollback(true)
    void transInsertTest() {
		int j=tdao.insert(t);
		assertNotEquals(0,j);
    }

}
