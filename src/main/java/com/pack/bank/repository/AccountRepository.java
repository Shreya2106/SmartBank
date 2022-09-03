package com.pack.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pack.bank.model.Account;

public interface AccountRepository extends JpaRepository<Account,Integer> {

	@Transactional
	@Modifying
	@Query(value="update Account e set e.active=false where e.account_number=:id",nativeQuery = true)
	int deleteCustom(@Param("id") Integer id);

	@Transactional
	@Modifying
	@Query(value="update Account e set e.balance=:bal where e.account_number=:acNo",nativeQuery = true)
	int setBalanceAfterTrans(@Param("bal") String bal, @Param("acNo") int acNo);

	@Query(value="select e.balance from Account e where e.account_number=:acNo",nativeQuery = true)
	String getBalanceFromAcNo(int acNo);

    @Query(value="SELECT count(*) from Account e where e.account_name=:acName and active=true and e.corporate_id=:id" ,nativeQuery = true)
	int checkACName(@Param("acName") String acName,@Param("id") int id);

	@Query(value="select * from Account e where e.corporate_id=:id and e.active=true",nativeQuery = true)
	List<Account> findACList(@Param("id") int id);

	@Transactional
	@Modifying
	@Query(value="update Account e set e.active=false where e.corporate_Id=:id",nativeQuery = true)
	void deleteByCorporateId(Integer id);

	@Query(value="select * from account a where a.corporate_id=(select u.corporate_id from user u where u.login_id=:id)",nativeQuery = true)
	List<Account> findByCorp(String id);
	
	@Query(value="select * from account where account_number=:acno",nativeQuery = true)
	Account findACDetails(int acno);

}

