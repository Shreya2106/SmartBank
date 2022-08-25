package com.pack.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.dao.AccountDao;
import com.pack.bank.model.Account;

@Service("accountManageDataImpl")
public class AccountManageDataImpl implements ManageData<Account,Integer>{
	
	@Autowired
	AccountDao adao;

	@Override
	public int delete(Integer id) {
		return adao.delete(id);
	}

	@Override
	public int add(Account data) {
		return adao.insert(data);
	}

	@Override
	public List<Account> viewAll(){
		return adao.viewAll();
	}
	
	public Boolean checkACName(String acName, int id) {
		return adao.checkACName(acName, id);
	}

	public String getBalanceFromAcNo(int acNo) {
		return adao.getBalanceFromAcNo(acNo);
	}

	public int setBalanceAfterTrans(String bal, int acNo) {
		return adao.setBalanceAfterTrans(bal, acNo);
	}
	

}
