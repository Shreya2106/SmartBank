package com.pack.bank;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.pack.bank.model.Account;
import com.pack.bank.model.Transaction;
import com.pack.bank.repository.AccountRepository;
import com.pack.bank.repository.TransactionRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class TransactionDaoTest {
	
	@Autowired
	TransactionRepository tdao;
	
	@Autowired
	AccountRepository adao;

	Transaction t;
	
	@BeforeEach
	void setUp() throws Exception{
		List<Account> aList= adao.findAll();
		Account accno = aList.get(aList.size()-1);
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
		Transaction j=tdao.save(t);
		assertNotEquals(null,j);
    }

}
