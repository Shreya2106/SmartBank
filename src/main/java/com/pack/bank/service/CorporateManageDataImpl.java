package com.pack.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.model.Corporates;
import com.pack.bank.repository.AccountRepository;
import com.pack.bank.repository.CorpoartesRepository;
import com.pack.bank.repository.UserRepository;

@Service("corporateManageDataImpl")
public class CorporateManageDataImpl implements ManageData<Corporates,Integer> {

	@Autowired
	CorpoartesRepository cRepo;
	
	@Autowired
	AccountRepository aRepo;
	
	@Autowired
	UserRepository uRepo;
	
	@Override
	public void add(Corporates data) {
		 cRepo.save(data);
	}

	@Override
	public List<Corporates> viewAll(){
		return cRepo.findAllCustom();
	}
	
	public void edit(Corporates c) {
		 cRepo.save(c);
	}

	@Override
	public int delete(Integer id) {
		uRepo.deleteByCorporateId(id);
		aRepo.deleteByCorporateId(id);
		return cRepo.softdeleteById(id);
	}
	
	public Corporates getCorpById(int id) {
		return cRepo.findByIdCustom(id);
	}

	public List<Integer> distinctId() {
		return cRepo.dintinctCorpId();
	}

	public int getCorpIdByUser(String email) {
		return cRepo.getIdByUser(email);
	}

	public String getCorpNameByUser(String email) {
		return cRepo.getNameByUser(email);
	}

	public List<String> distinctName() {
		return cRepo.dintinctCorpName();
	}

}
