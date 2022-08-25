package com.pack.bank;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.pack.bank.dao.CorporateDao;
import com.pack.bank.model.Corporates;

class CorporateDaoTest {

	CorporateDao cdao;
	Corporates c;
	
	@BeforeEach
	@Transactional
	@Rollback(true)
	void init() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("config-test.xml");
		cdao = context.getBean("corporateDao", CorporateDao.class);
		((ClassPathXmlApplicationContext) context).close();
		c = new Corporates("Amazon","Hyderabad,India","9876421305");
	}
	

	@Test
    @Transactional
    @Rollback(true)
    void corpInsertTest() {
		int j=cdao.insert(c);
		assertNotEquals(0,j);
    }

	@Test
	@Transactional
	@Rollback(true)
	void corpModifyTest() {
		List<Corporates> cList= cdao.viewAll();
		int id= cList.get(cList.size()-1).getCorporateId();
		Corporates c1=cdao.getCorpById(id);
		c1.setAddress("newAddress");
		int j= cdao.modify(c1);
		assertNotEquals(0,j); 
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void corpDeleteTest() {
		List<Corporates> cList= cdao.viewAll();
		int id= cList.get(cList.size()-1).getCorporateId();
		int j= cdao.delete(id);
		assertNotEquals(0,j); 
	}
}
