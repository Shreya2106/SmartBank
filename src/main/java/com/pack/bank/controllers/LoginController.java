package com.pack.bank.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.bank.model.Account;
import com.pack.bank.model.Transaction;
import com.pack.bank.service.AdminLoginServiceImpl;
import com.pack.bank.service.UserLoginServiceImpl;

@Controller
public class LoginController {
	
	/*String constants that have been used frequently*/
	public static final String ERRORMSG="errorMsg";
	public static final String SUCCESSMSG="successMsg";
	public static final String PARAMMSG="Mandatory Parameter missing";
	public static final String LOGINERRORS="loginerrors";
	public static final String USERHOME="redirect:userhomes";
	public static final String TRANSACTION="transaction";
	public static final String EMPTY="empty";
	private static final String ACCOUNT = "account";
	
	private static final Logger LOGGER=LoggerFactory.getLogger(LoginController.class);
	
	
	@Autowired
	AdminLoginServiceImpl alsi;
	
	@Autowired
	UserLoginServiceImpl ulsi;
	
	@RequestMapping(value={"/","login"})
	public String start(HttpSession session) {
		session.setAttribute(SUCCESSMSG,EMPTY);
		return "login";
	}
	
	
	/*After the login form is submitted its request is mapped here and based on the type of user the home is rendered*/
	@RequestMapping("loginprocess")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password,HttpSession session,Model m) throws LoginException, NoSuchUserException,ParamMissingException {
		session.setAttribute(SUCCESSMSG,EMPTY);
		if(email == null || "".equals(email) || password == null || "".equals(password)){
			throw new ParamMissingException(PARAMMSG); //Custom Login Exception thrown when the mandatory parameters are missing.
		}
	  else {
		try{
			if(alsi.validateUser(email,password)) { //Firstly the user is validated with the back office users' credentials
				session.setAttribute("name",alsi.getUsername(email));
				session.setAttribute("email",email);
				session.setAttribute("userType","back");
				LOGGER.info("\n\n****Logged in as Back Office User****\n");
				return "redirect:backofficehome";
			}
		}
		/*If the validation fails due to wrong credentials the exception is mapped globally*/
		
		catch(NoSuchUserException e1) { //If the validation fails because of no such back office user
			LOGGER.info("\n\n*****No such back office user*****\n");
			 return uservalidate(email,password,session,m);
		}
	  }
	  return LOGINERRORS;
	}
		
	/*When the login form is submitted and the back office user validation fails its mapped here for front office user validation*/
	public String uservalidate(@RequestParam("email") String email, @RequestParam("password") String password,HttpSession session,Model m) throws LoginException, NoSuchUserException {
			session.setAttribute(SUCCESSMSG,EMPTY);
			try{ 
				if(ulsi.validateUser(email,password)) {    //The user is then validated with the front office users' credentials
					LOGGER.info("\n\n****Logged in as Front Office User****\n");
					session.setAttribute("name",ulsi.getUsername(email));
					session.setAttribute("email",email);
					session.setAttribute("userType","front");
					boolean status = ulsi.getStatus(email); //Checking the user is logging in for first time
					if(!status) 
						return "FrontOffice/frontofficehome"; //First time user are redirected to change password
					else{
						m.addAttribute(TRANSACTION,new Transaction());
						m.addAttribute(ACCOUNT,new Account());
						return USERHOME;
					}
				}
			}
			/*If the validation fails due to wrong credentials the exception is mapped globally*/
		   catch(NoSuchUserException e3) {
			   LOGGER.info("\n\n*****No such user\n\n");
			   session.setAttribute(ERRORMSG,"No such user");
				return LOGINERRORS;
		   }
			return LOGINERRORS;
	}
	
	
	/*When a request is made to logout and terminate the session its mapped here*/
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		LOGGER.info("\n****Logged out****");
		return "redirect:login";
	}
	
	
	/*If there is any exception/errors in login form or globally its mapped here*/
	@RequestMapping("/loginerrors")
	public String loginErrorsLoad(HttpSession session) {
		session.setAttribute(ERRORMSG,"Sorry you have logged out!! Need to login again to access.");
		return LOGINERRORS;
	}
	
	
	@RequestMapping("backofficehome")
	public String loadBackOffice(Model m) {  
		return "backofficehome";
	}
	
}
