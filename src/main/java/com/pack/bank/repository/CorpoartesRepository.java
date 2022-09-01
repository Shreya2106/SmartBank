package com.pack.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pack.bank.model.Corporates;

public interface CorpoartesRepository extends JpaRepository<Corporates,Integer>{

	@Transactional
	@Modifying
	@Query(value="update Corporates e set e.softdeleted=true where e.corporate_id=:id",nativeQuery = true)
	int softdeleteById(@Param("id") Integer id);

	@Query(value="select * from Corporates e where e.softdeleted=false",nativeQuery = true)
	List<Corporates> findAllCustom();

	@Query(value="select distinct(e.corporate_id) from Corporates e where e.softdeleted=false",nativeQuery = true)
	List<Integer> dintinctCorpId();

	@Query(value="select c.corporate_id from corporates c where c.corporate_id=(select u.corporate_id from user u where u.login_id=:email)",nativeQuery = true)
	int getIdByUser(String email);
	
	@Query(value="select c.corporate_name from corporates c where c.corporate_id=(select u.corporate_id from user u where u.login_id=:email)",nativeQuery = true)
	String getNameByUser(String email);

	@Query(value="select distinct(e.corporate_name) from Corporates e where e.softdeleted=false",nativeQuery = true)
	List<String> dintinctCorpName();

	@Query(value="select * from Corporates e where e.softdeleted=false and e.corporate_id=:id",nativeQuery = true)
	Corporates findByIdCustom(int id);

}
