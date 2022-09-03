package com.pack.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.model.Account;
import com.pack.bank.repository.AccountRepository;

@Service("accountManageDataImpl")
public class AccountManageDataImpl implements ManageData<Account,Integer>{
	
	@Autowired
	AccountRepository aRepo;

	@Override
	public int delete(Integer id) {
		return aRepo.deleteCustom(id);
	}

	@Override
	public void add(Account data) {
		aRepo.save(data);
	}

	@Override
	public List<Account> viewAll(){
		return aRepo.findAll();
	}
	
	public String getBalanceFromAcNo(int acNo) {
		return aRepo.getBalanceFromAcNo(acNo);
	}

	public int setBalanceAfterTrans(String bal, int acNo) {
		return aRepo.setBalanceAfterTrans(bal, acNo);
	}
	public Boolean checkACName(String acName, int id) {
		return (aRepo.checkACName(acName, id)!=0);
	}
	
	public List<Account> findList(int id){
		return aRepo.findACList(id);
	}

	public List<Account> viewAccountByCorp(String id) {
		return aRepo.findByCorp(id);
	}
	
	public Account viewDetails(int acno) {
		return aRepo.findACDetails(acno);
	}
}
