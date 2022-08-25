package com.pack.bank.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.bank.dao.AccountDao;
import com.pack.bank.dao.BackOfficeUserDao;
import com.pack.bank.dao.CorporateDao;
import com.pack.bank.dao.TransDao;
import com.pack.bank.dao.UserDao;
import com.pack.bank.model.Transaction;

@Controller
public class SmartBankController {
	
	/*String constants that have been used frequently*/
	public static final String ERRORMSG="errorMsg";
	public static final String SUCCESSMSG="successMsg";
	public static final String PARAMMSG="Mandatory Parameter missing";
	public static final String LOGINERRORS="loginerrors";
	public static final String USERHOME="redirect:userhome";
	public static final String TRANSACTION="transaction";
	public static final String EMPTY="empty";
	
	@Autowired
	BackOfficeUserDao bdao;
	
	@Autowired
	UserDao udao;
	
	@Autowired
	CorporateDao cdao;
	
	@Autowired
	AccountDao adao;
	
	@Autowired
	TransDao tdao;
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
	@RequestMapping("/login")
	public String login(HttpSession session) {
		session.setAttribute(SUCCESSMSG,EMPTY);
		return "../../login";
	}
	@RequestMapping("/loginerrors")
	public String loginErrorsLoad(HttpSession session) {
		session.setAttribute(ERRORMSG,"Sorry you have logged out!! Need to login again to access.");
		return LOGINERRORS;
	}
	
	/*After the login form is submitted its request is mapped here and based on the type of user the home is rendered*/
	@RequestMapping("/loginprocess")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,HttpSession session,Model m) throws LoginException{ 
		session.setAttribute(SUCCESSMSG,EMPTY);
		if(email == null || "".equals(email) || password == null || "".equals(password)){
			throw new LoginException(PARAMMSG); //Custom Login Exception thrown when the mandatory parameters are missing.
		}
	  else {
		try {
		  //BackOfficeUser's object is obtained from the table using the email/loginId
		      if(bdao.validateUser(email,password)){ 
				//If the password provided and the password obtained from table matches BackOffice Home page is rendered
				session.setAttribute("name",bdao.getUsername(email));
				session.setAttribute("email",email);
				session.setAttribute("userType","back");
				return "redirect:backofficehome"; 
			}
		}
		/*If the validation fails due to wrong credentials the exception is mapped globally*/
		
		catch(NoSuchUserException e1) { //If the validation fails because of no such back office user
			 return uservalidate(email,password,session,m);
		}
	  }
	  return LOGINERRORS;
	}
	
	
    private String uservalidate(@RequestParam("email") String email, @RequestParam("password") String password,HttpSession session, Model m) {
    	session.setAttribute(SUCCESSMSG,EMPTY);
		try{ 
			if(udao.validateUser(email,password)) {    //The user is then validated with the front office users' credentials
				session.setAttribute("name",udao.getUsername(email));
				session.setAttribute("email",email);
				session.setAttribute("userType","front");
				int status = udao.getStatus(email); //Checking the user is logging in for first time
				if(status==0) 
					return "FrontOffice/frontofficehome"; //First time user are redirected to change password
				else{
					m.addAttribute(TRANSACTION,new Transaction());
					return USERHOME;
				}
			}
		}
		/*If the validation fails due to wrong credentials the exception is mapped globally*/
	   catch(NoSuchUserException e3) {
		   session.setAttribute(ERRORMSG,"No such user");
			return LOGINERRORS;
	   }
		return LOGINERRORS;
}

	
	      /* ********BACK OFFICE******* */
	
	@RequestMapping("/backofficehome")
	public String loadBackOffice(Model m) {  
		return "backofficehome";
	}
	
}