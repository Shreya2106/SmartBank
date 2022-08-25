package com.pack.bank.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.bank.model.Transaction;
import com.pack.bank.service.AccountManageDataImpl;
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
	public static final String USERHOME="redirect:userhome";
	public static final String TRANSACTION="transaction";
	public static final String TRANSERROR="FrontOffice/transError";
	
	@Autowired
	UserManageDataImpl umdl;
	
	@Autowired
	AccountManageDataImpl amdl;
	
	@Autowired
	TransactionServiceImpl tmdl;
	
	 @RequestMapping(value="/trans")
	  public String transactions(@RequestParam String id,Model m,HttpSession session) {
		  session.setAttribute("cid",id);
		  return "AccountSetUp/trans";
	  }
	  //For first time user password change page is mapped here
	  @RequestMapping(value="changePass")
	  public String changePassword(@RequestParam("password1") String password1,@RequestParam("password2") String password2,HttpSession session,Model m) {
		  String email = (String) session.getAttribute("email");
		  if((password1 == null || "".equals(password1)) || (password2 == null || "".equals(password2))){   
			     session.setAttribute(ERRORMSG,PARAMMSG); 
			     return PASSERROR;
			}
		  else {
			  //Password must be of the specific format
			  Pattern p = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$");
			  Matcher m1 = p.matcher(password1);
			  Matcher m2 = p.matcher(password2);
		      if (!m1.matches() || !m2.matches()) { //If the password doesn't match with the requirements
		    	  session.setAttribute(ERRORMSG,"Password must contain a uppercase, a lowercase, a digit and atleast 8 characters"); 
		    	  return PASSERROR;
		      }
		      else if(password1.equals(password2)){ 
		    	//When password matches the format
		    	//The user must be sure of the password entered so two fields were provide to enter the new password and both fields should match
		    	  	int res = umdl.changePass(email,password1);
		    		  if (res>=1){
		    			  return USERHOME;
		    		  }
		    		  else{
		    			  	session.setAttribute(ERRORMSG,"Oops couldn't change!!");
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
	  public String addTransaction(Transaction a,HttpSession session) {
		  String transDate = a.getTransactionDate();
		  String balance = a.getBalance();
		  int acNo = a.getAccountNumber();
		  String transType = a.getTransactionType();
		  if(transDate == null || "".equals(transDate) || balance == null || "".equals(balance)){ 
			     Object obj = new Transaction();
				 throw new DataHandleException(PARAMMSG,TRANSERROR,obj,TRANSACTION);
			}
		  int amt = Integer.parseInt(amdl.getBalanceFromAcNo(acNo)); //fetching the current balance of the corporate for whom the transaction is to be added
		  if(Integer.parseInt(balance)>amt && transType.equals("credit")) {
			  //checking whether the amount to be credited is within the c/o balance for that a/c
			  Object obj = new Transaction();
			  throw new DataHandleException("Amount cannot be greater than balance.",TRANSERROR,obj,TRANSACTION);
		  }
		  else {
			  //If the amount of the transaction is within balance credit is done
			  if(transType.equals("credit")) 
				  amt = amt - Integer.parseInt(balance);
			  else
				  //Balance doesn't matter if the transaction type is debit
				  amt = Integer.parseInt(balance) + amt;
		      int scode = amdl.setBalanceAfterTrans(Integer.toString(amt),acNo); //status code obtained after the balance of the accounts table is changed
		      int res= tmdl.insert(a); //status code obtained after the values have been added to the transactions table
				 if (res>=1 && scode>=1){
						session.setAttribute(SUCCESSMSG,ADDSUCCESSMSG);
						return USERHOME;
					}
					else{
						  Object obj = new Transaction();
						  throw new DataHandleException(ERRORADDMSG,TRANSERROR,obj,TRANSACTION);
					} 
		  }  
	  }
	  @RequestMapping(value="/accdetails")
	  public String accDetails(@RequestParam String id,Model m,HttpSession session) {
		  session.setAttribute("cid",id);
		  return "FrontOffice/accdetails";
	  }
	  @RequestMapping(value="/transdetails")
	  public String transDetails(@RequestParam String id,Model m,HttpSession session) {
		  session.setAttribute("cid",id);
		  return "FrontOffice/transdetails";
	  }
	  @RequestMapping(value="/userhome")
	  public String userHomeLoad(Model m) {
		  m.addAttribute(TRANSACTION,new Transaction());
		  return "FrontOffice/userhome";
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
