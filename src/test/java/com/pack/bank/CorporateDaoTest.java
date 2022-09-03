package com.pack.bank;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.pack.bank.model.Corporates;
import com.pack.bank.repository.CorpoartesRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class CorporateDaoTest {

	@Autowired
	CorpoartesRepository cRepo;
	Corporates c;
	
	@BeforeEach
	void setUp() throws Exception{
		c = new Corporates("Amazon","Hyderabad,India","9876421305");
	}
	
	@AfterEach
	void tearDown() throws Exception{
		c = null;
	}

	@Test
    @Transactional
    @Rollback(true)
	@Order(1)
    void corpInsertTest() {
		Corporates j=cRepo.save(c);
		assertNotEquals(null,j);
    }

	@Test
	@Transactional
	@Rollback(true)
	@Order(2)
	void corpModifyTest() {
		List<Corporates> cList= cRepo.findAll();
		int id= cList.get(cList.size()-2).getCorporateId();
		Corporates c1=cRepo.findByIdCustom(id);
		c1.setAddress("newAddress");
		Corporates j= cRepo.save(c1);
		assertNotEquals(null,j); 
	}
	
	@Test
	@Transactional
	@Rollback(true)
	@Order(3)
	void corpDeleteTest() {
		List<Corporates> cList= cRepo.findAll();
		int id= cList.get(cList.size()-1).getCorporateId();
		int j= cRepo.softdeleteById(id);
		assertNotEquals(0,j); 
	}
}
