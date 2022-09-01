package com.pack.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pack.bank.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
	@Query("select e.username from User e where e.loginId=:email")
	String getUserName(@Param("email") String email);
	
	
	@Query("select e.changed from User e where e.loginId=:email")
	boolean getStatus(@Param("email") String email);

	@Transactional
	@Modifying
	@Query(value="update User e set e.password=:password1, e.changed=true where e.login_id=:email",nativeQuery = true)
	int changePass(@Param("email") String email, @Param("password1") String password1);

	@Query("select e from User e where e.loginId=:ide")
	User getUserByEmail(@Param("ide") String ide);

	@Transactional
	@Modifying
	@Query("delete from User e where e.loginId=:id")
	int deleteByEmail(String id);

	@Transactional
	@Modifying
	@Query(value="update User e set e.address=:add, e.contact_number=:ph where e.login_id=:email",nativeQuery = true)
	int updateCustom(@Param("add") String add,@Param("ph") String ph,@Param("email") String email);


	@Query(value="select * from user e",nativeQuery = true)
	List<User> findAllCustom();


	@Transactional
	@Modifying
	@Query(value="delete from User e where e.corporate_id=:id",nativeQuery = true)
	void deleteByCorporateId(Integer id);

}
