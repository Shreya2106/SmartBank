package com.pack.bank.controllers;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.pack.bank.service.UserManageDataImpl;

@Controller
public class FrontOfficeController {
	
	public static final String ADDSUCCESSMSG="Successfully Added!!";
	public static final String ERRORADDMSG="Oops couldn't add!!";
	public static final String ERRORMSG="errorMsg";
	public static final String SUCCESSMSG="successMsg";
	public static final String PARAMMSG="Mandatory Parameter missing";
	public static final String FALSE="false";
	public static final String TRUE="true";
	public static final String PASSERROR="redirect:passError";
	public static final String USERHOME="redirect:userhomes";
	public static final String TRANSACTION="transaction";
	public static final String TRANSERROR="FrontOffice/transError";
	private static final String ACCOUNT = "account";
	private static final String RESULT = "result";
	public static final String TRANSERRORV="transerror";
	
	private static final Logger LOGGER=LoggerFactory.getLogger(FrontOfficeController.class);
	
	@Autowired
	UserManageDataImpl umdl;
	
	@Autowired
	AccountManageDataImpl amdl;
	
	@Autowired
	CorporateManageDataImpl cmdl;
	
	@Autowired
	TransactionServiceImpl tmdl;
	
	  //For first time user password change page is mapped here
	  @RequestMapping(value="changePass")
	  public String changePassword(@RequestParam("password1") String password1,@RequestParam("password2") String password2,HttpSession session,Model m) {
		  String email = (String) session.getAttribute("email");
		  if((password1 == null || "".equals(password1)) || (password2 == null || "".equals(password2))){   
			     session.setAttribute(ERRORMSG,PARAMMSG); 
			     LOGGER.info("\n\n****Oops mandatory parameter missing****\n");
			     return PASSERROR;
			}
		  else {
			  //Password must be of the specific format
			  Pattern p = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$");
			  Matcher m1 = p.matcher(password1);
			  Matcher m2 = p.matcher(password2);
		      if (!m1.matches() || !m2.matches()) { //If the password doesn't match with the requirements
		    	  session.setAttribute(ERRORMSG,"Password must contain a uppercase, a lowercase, a digit and atleast 8 characters"); 
		    	  LOGGER.info("\n\n****Oops there was some server side validation error in  password update form****\n");
		    	  return PASSERROR;
		      }
		      else if(password1.equals(password2)){ 
		    	//When password matches the format
		    	//The user must be sure of the password entered so two fields were provide to enter the new password and both fields should match
		    	  	int res = umdl.changePass(email,password1);
		    		  if (res>=1){
		    			  session.setAttribute(TRANSERRORV,FALSE);
		    			  LOGGER.info("\n\n****Password changed successfully****\n");
		    			  return USERHOME;
		    		  }
		    		  else{
		    			  	session.setAttribute(ERRORMSG,"Oops couldn't change!!");
		    			  	LOGGER.info("\n\n****Oops there was some error while updating password****\n");
		    			  	return PASSERROR;
		    		  	}
		    	  }
		    	else {
		    		  session.setAttribute(ERRORMSG,"Both passwords should be same.");
		    		  return PASSERROR;
		    	}
		  }
		    		  
	  }
	  //Transaction Add
	  @PostMapping(value = "/transadd") 
	  public String addTransaction(@ModelAttribute("transaction") Transaction t,HttpSession session,Model m) {
		  String transDate = t.getTransactionDate();
		  String balance = t.getBalance();
		  int acNo = t.getAccount().getAccountNumber();
		  String transType = t.getTransactionType();
		  if(transDate == null || "".equals(transDate) || balance == null || "".equals(balance)){ 
			     Object obj1 = new Transaction();
			     Object obj2 = new Account();
			     session.setAttribute(TRANSERRORV,TRUE);
			     LOGGER.info("\n\n****Oops mandatory parameter missing****\n");
				 throw new TransHandleException(PARAMMSG,TRANSERROR,obj1,obj2,TRANSACTION,ACCOUNT);
			}
		  int amt = Integer.parseInt(amdl.getBalanceFromAcNo(acNo)); //fetching the current balance of the corporate for whom the transaction is to be added
		  if(Integer.parseInt(balance)>amt && transType.equals("credit")) {
			  //checking whether the amount to be credited is within the c/o balance for that a/c
			  Object obj1 = new Transaction();
			  Object obj2 = new Account();
			  session.setAttribute(TRANSERRORV,TRUE);
			  LOGGER.info("\n\n****Oops amount cannot be greater than balance.****\n");
			  throw new TransHandleException("Amount cannot be greater than balance.",TRANSERROR,obj1,obj2,TRANSACTION,ACCOUNT);
		  }
		  else {
			  //If the amount of the transaction is within balance credit is done
			  if(transType.equals("credit")) 
				  amt = amt - Integer.parseInt(balance);
			  else
				  //Balance doesn't matter if the transaction type is debit
				  amt = Integer.parseInt(balance) + amt;
		      int scode = amdl.setBalanceAfterTrans(Integer.toString(amt),acNo); //status code obtained after the balance of the accounts table is changed
		      tmdl.insert(t); //status code obtained after the values have been added to the transactions table
				 if (scode>=1){
						session.setAttribute(SUCCESSMSG,ADDSUCCESSMSG);
						session.setAttribute(TRANSERRORV,FALSE);
						LOGGER.info("\n\n****Transaction added successfully.****\n");
						return USERHOME;
					}
					else{
						 Object obj1 = new Transaction();
						  Object obj2 = new Account();
						  LOGGER.info("\n\n****Oops couldn't add the transaction there was some error.****\n");
						  throw new TransHandleException(ERRORADDMSG,TRANSERROR,obj1,obj2,TRANSACTION,ACCOUNT);
					} 
		  }  
	  }
	  
	  /**/
	  @RequestMapping(value="/accdetails")
	  public ModelAndView accDetails(ModelAndView mav,@RequestParam int id,Model m,HttpSession session) {
		  session.setAttribute("accno",id);
		  Account a = amdl.viewDetails(id);
		  m.addAttribute(RESULT,a);
		  mav.setViewName("FrontOffice/accdetails");
		  return mav;
	  }
	  @RequestMapping(value="/transdetails")
	  public ModelAndView transDetails(ModelAndView mav,@RequestParam int id,Model m,HttpSession session) {
		  session.setAttribute("cid",id);
		  List<Transaction> t = tmdl.viewDetails(id);
		  mav.addObject(RESULT,t);
		  mav.setViewName("FrontOffice/transdetails");
		  return mav;
	  }
	  @RequestMapping(value="/userhomes")
	  public ModelAndView userHomeLoad(ModelAndView mav,Model m,HttpSession session){
		   String email = (String) session.getAttribute("email");
	        List<Account> result = amdl.viewAccountByCorp(email);
	        int cid = cmdl.getCorpIdByUser(email);
	        String cname = cmdl.getCorpNameByUser(email);
	        session.setAttribute("cname", cname);
	        session.setAttribute("cid", cid);
	        mav.addObject(RESULT,result);
	        m.addAttribute(TRANSACTION,new Transaction());
			m.addAttribute(ACCOUNT,new Account());
	        mav.setViewName("FrontOffice/userhome");
	        return mav;
	    }
	  @RequestMapping(value="/passError")
	  public String passErrorLoad(Model m) {
		  return "FrontOffice/passError";
	  }
	  @RequestMapping(value="/frontofficehome")
	  public String loadFrontOffice() {
		  return "FrontOffice/frontofficehome";
	  }
}
