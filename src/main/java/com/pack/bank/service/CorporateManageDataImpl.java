package com.pack.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.bank.dao.CorporateDao;
import com.pack.bank.model.Corporates;

@Service("corporateManageDataImpl")
public class CorporateManageDataImpl implements ManageData<Corporates,Integer> {

	@Autowired
	CorporateDao cdao;
	
	@Override
	public int add(Corporates data) {
		return cdao.insert(data);
	}

	@Override
	public List<Corporates> viewAll(){
		return cdao.viewAll();
	}
	
	public int edit(Corporates c) {
		return cdao.modify(c);
	}

	@Override
	public int delete(Integer id) {
		return cdao.delete(id);
	}
	
	public Corporates getCorpById(int id) {
		return cdao.getCorpById(id);
	}

}
