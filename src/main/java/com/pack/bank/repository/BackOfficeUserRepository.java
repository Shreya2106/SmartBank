package com.pack.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pack.bank.model.BackOfficeUser;

public interface BackOfficeUserRepository extends JpaRepository<BackOfficeUser,Integer> {
	
	@Query("select e.username from BackOfficeUser e where e.loginId=:email")
	String getUserName(@Param("email") String email);
	
	@Query("select e from BackOfficeUser e where e.loginId=:ide")
	BackOfficeUser getUserByEmail(@Param("ide") String ide);
	
	@Query(value="select count(*) from back_office_user e",nativeQuery=true)
	int checkEmpty();

}
