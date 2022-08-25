package com.pack.bank.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.bank.dao.AccountDao;
import com.pack.bank.model.Account;

@Controller
public class AccountController {
	
	/*String constants that have been used frequently*/
	public static final String ADDSUCCESSMSG="Successfully Added!!";
	public static final String ERRORADDMSG="Oops couldn't add!!";
	public static final String DELETESUCCESSMSG="Successfully deleted!!";
	public static final String ERRORDELETEMSG="Oops couldn't delete!!";
	public static final String UPDATESUCCESSMSG="Successfully Updated!!";	
	public static final String ERRORUPDATEMSG="Oops couldn't update!!";
	public static final String ERRORMSG="errorMsg";
	public static final String SUCCESSMSG="successMsg";
	public static final String PARAMMSG="Mandatory Parameter missing";
	public static final String FALSE="false";
	public static final String TRUE="true";
	public static final String ACCERROR="AccountSetUp/accountError";
	public static final String ACCOUNT="account";
	
	@Autowired
	AccountDao adao;
	
	/* ********ACCOUNT SET UP******* */
	
	 @RequestMapping("/account")
	 public String loadAccount() {
	 	return "AccountSetUp/account";
	 }
	  @RequestMapping(value="/accountslist")
	  public String accList(@RequestParam int id,Model m,HttpSession session) {
		  session.setAttribute("cid",id);
		  return "AccountSetUp/accountslist";
	  }
	  @RequestMapping(value="/accountadd")
	  public String accAdd(@RequestParam int id,Model m,HttpSession session) {
		  m.addAttribute(ACCOUNT, new  Account()); 
		  session.setAttribute("cid",id);
		  return "AccountSetUp/accountadd";
	  }
	  @PostMapping(value = "/addaccount") 
	  public String addAccount(Account a,HttpSession session) {
		  String acName = a.getAccountName();
		  String bal = a.getBalance();
		  String obal = a.getOpeningBalance();
		  String cbal = a.getClosingBalance();
		  int id = a.getCorporateId();
		  session.setAttribute("cid", id);
		  Boolean res = adao.checkACName(acName,id); //Finding whether the a/c name exists or not of the given corporate
		  if(acName == null || "".equals(acName) || bal == null || "".equals(bal)){   //Checking whether the mandatory parameters are missing  
			     Object obj = new Account();
				 throw new DataHandleException(PARAMMSG,ACCERROR,obj,ACCOUNT);
			}
		  else if(Boolean.TRUE.equals(res)) { //Since a/c name has to be unique custom exception is thrown
			     Object obj = new Account();
				 throw new DataHandleException("A/C name already exists",ACCERROR,obj,ACCOUNT);
		  }
		  else if(Integer.parseInt(bal)<Integer.parseInt(obal) || Integer.parseInt(bal)>Integer.parseInt(cbal)) { 
			  //checking whether balance lies within o/p balance and c/n balance
			     Object obj = new Account();
				 throw new DataHandleException("Balance should be more than opening balance and less than closing balance",ACCERROR,obj,ACCOUNT);
		  }
		  else {
			  //if the account add form has no flaws data is added to db
			  int i= adao.insert(a);
			  if (i>=1){
				session.setAttribute(SUCCESSMSG,ADDSUCCESSMSG);
				return "redirect:accountslist?id="+id;
			  }
			  else{
				 Object obj = new Account();
				 throw new DataHandleException(ERRORADDMSG,ACCERROR,obj,ACCOUNT);
			  }
		  }
	  }
	  @RequestMapping(value="/acdelete")
	  public String accClose(@RequestParam int id,HttpSession session) {
		  int res=adao.delete(id); // a/c is soft-deleted from the db  i.e it exists in the db but wont be seen/active to users
			if (res>=1){
				session.setAttribute(SUCCESSMSG,"Successfully Closed!!");
				int cid=(int)session.getAttribute("cid");
				return "redirect:accountslist?id="+cid;
			}
			else{
				 Object obj = new Account();
				 throw new DataHandleException(ERRORDELETEMSG,ACCERROR,obj,ACCOUNT);
			} 
	  }

}
