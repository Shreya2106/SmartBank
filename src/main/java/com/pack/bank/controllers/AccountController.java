package com.pack.bank.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pack.bank.model.Account;
import com.pack.bank.model.Transaction;
import com.pack.bank.service.AccountManageDataImpl;
import com.pack.bank.service.CorporateManageDataImpl;
import com.pack.bank.service.TransactionServiceImpl;

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
	private static final String ACERROR = "accerror";
	
	private static final Logger LOGGER=LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	AccountManageDataImpl amdl;
	
	@Autowired
	TransactionServiceImpl tmdl;
	
	@Autowired
	CorporateManageDataImpl cmdl;
	
	@RequestMapping("/account")
	 public ModelAndView loadAccount(ModelAndView mav,HttpSession session) {
		session.setAttribute(ACERROR,FALSE);
		List<Integer> cid = cmdl.distinctId();
		List<String> cname = cmdl.distinctName();
		 mav.addObject("cid", cid);
		 mav.addObject("cname", cname);
		 session.setAttribute("cids",cid);
		 session.setAttribute("cname",cname);
		mav.setViewName("AccountSetUp/account");
		return mav;
	 }
	
	
	 /*When there is a request for fetching the lists of the account's of a particular corporate its mapped here*/
	  @RequestMapping(value="/accountslists")
	  public ModelAndView accList(@RequestParam int id,Model m,HttpSession session,ModelAndView mav) {
	        List<Account> result = amdl.findList(id);
	        mav.addObject("result", result);
	        session.setAttribute("cid",id);
	        mav.setViewName("AccountSetUp/accountslist");
	        return mav;
	    }
	  
	  /*When there is a request for the account add  form its mapped here*/
	  @RequestMapping(value="/accountadd")
	  public String accAdd(@RequestParam int id,Model m,HttpSession session) {
		  m.addAttribute(ACCOUNT, new  Account()); 
		  session.setAttribute("cid",id);
		  return "AccountSetUp/accountadd";
	  }
	  
	  
	  /*When account add  form is submitted its mapped here*/
	  @PostMapping(value = "/addaccount") 
	  public String addAccount(@ModelAttribute("account")Account a,HttpSession session) {
		  String acName = a.getAccountName();
		  String bal = a.getBalance();
		  String obal = a.getOpeningBalance();
		  String cbal = a.getClosingBalance();
		  int id = a.getCorporate().getCorporateId();
		  session.setAttribute("cid", id);
		  Boolean res = amdl.checkACName(acName,id); //Finding whether the a/c name exists or not of the given corporate
		  if(acName == null || "".equals(acName) || bal == null || "".equals(bal)){   //Checking whether the mandatory parameters are missing  
			     Object obj = new Account();
			     session.setAttribute(ACERROR,TRUE);
			     LOGGER.info("\n\n****Oops mandatory parameter missing****\n");
				 throw new DataHandleException(PARAMMSG,ACCERROR,obj,ACCOUNT);
			}
		  else if(Boolean.TRUE.equals(res)) { //Since a/c name has to be unique custom exception is thrown
			     Object obj = new Account();
			     session.setAttribute(ACERROR,TRUE);
			     LOGGER.info("\n\n****Oops A/C name already exists****\n");
			     throw new DataHandleException("A/C name already exists",ACCERROR,obj,ACCOUNT);
		  }
		  else if(Integer.parseInt(bal)<Integer.parseInt(obal) || Integer.parseInt(bal)>Integer.parseInt(cbal)) { 
			  //checking whether balance lies within o/p balance and c/n balance
			     Object obj = new Account();
			     session.setAttribute(ACERROR,TRUE);
			     LOGGER.info("\n\n****Oops Balance should be more than opening balance and less than closing balance.****\n");
				 throw new DataHandleException("Balance should be more than opening balance and less than closing balance",ACCERROR,obj,ACCOUNT);
		  }
		  else {
			  //if the account add form has no flaws data is added to db
			  a.setActive(1);
			  amdl.add(a);
			  session.setAttribute(SUCCESSMSG,ADDSUCCESSMSG);
			  LOGGER.info("\n\n****Account added successfully****\n");
			  int cid = (int) session.getAttribute("cid");
			  return "redirect:accountslists?id="+cid;
		  }
	  }
	  
	  
	  /*When any particular account of a corporate is soft-deleted its mapped here*/
	  @RequestMapping(value="/acdelete")
	  public String accClose(@RequestParam int id,HttpSession session) {
		 int res = amdl.delete(id); // a/c is soft-deleted from the db  i.e it exists in the db but wont be seen/active to users
		 if (res>=1){
				session.setAttribute(SUCCESSMSG,"Successfully Closed!!");
				int cid=(int)session.getAttribute("cid");
				LOGGER.info("\n\n****Account closed successfully****\n");
				return "redirect:accountslists?id="+cid;
			}
			else{
				 Object obj = new Account();
				 LOGGER.info("\n\n****Oops there was some error in  closing account****\n");
				 throw new DataHandleException(ERRORDELETEMSG,ACCERROR,obj,ACCOUNT);
			} 
	  }
	  
	  /*When there is a request for fetching the lists of the transaction's of a particular account its mapped here*/
	  @RequestMapping(value="/trans")
	  public ModelAndView transDetails(ModelAndView mav,@RequestParam int id,Model m,HttpSession session) {
		  session.setAttribute("cid",id);
		  List<Transaction> t = tmdl.viewDetails(id);
		  mav.addObject("result",t);
		  mav.setViewName("AccountSetUp/trans");
		  return mav;
	  }
}
