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
import com.pack.bank.dao.TransDao;
import com.pack.bank.model.Account;
import com.pack.bank.model.Transaction;

@SpringBootTest
class TransactionDaoTest {
	
	@Autowired
	TransDao tdao;
	
	@Autowired
	AccountDao adao;

	Transaction t;
	
	@BeforeEach
	void setUp() throws Exception{
		List<Account> aList= adao.viewAll();
		int accno = aList.get(aList.size()-1).getAccountNumber();
		t = new Transaction(accno,"Debit","2022-08-08","600");
	}
	
	@AfterEach
	void tearDown() throws Exception{
		t = null;
	}
	
	@Test
    @Transactional
    @Rollback(true)
    void transInsertTest() {
		int j=tdao.insert(t);
		assertNotEquals(0,j);
    }

}
