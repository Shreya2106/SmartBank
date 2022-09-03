package com.pack.bank.controllers;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pack.bank.exceptions.ParamMissingException;
import com.pack.bank.model.Account;
import com.pack.bank.model.Transaction;
import com.pack.bank.service.AdminLoginServiceImpl;
import com.pack.bank.service.CurrentUserDetails;
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
	private static final String EMAIL = "email";
	
	private static final Logger LOGGER=LoggerFactory.getLogger(LoginController.class);
	
	
	@Autowired
	AdminLoginServiceImpl alsi;
	
	@Autowired
	UserLoginServiceImpl ulsi;
	
	@GetMapping("/login")
	public String login(HttpSession session) {
		session.setAttribute(SUCCESSMSG,EMPTY);
		return "login";
	}
	
	@GetMapping("/loginerrors")
	public String loginerrorspage(HttpSession session) {
		session.setAttribute(ERRORMSG,"Invalid Credentials. Please provide valid credentials");
		session.setAttribute(SUCCESSMSG,EMPTY);
		return LOGINERRORS;
	}
	
	
	@RequestMapping("/")
	public String process(HttpSession session,Model m) throws ParamMissingException {
		LOGGER.info("The form is authenticated");
		session.setAttribute(SUCCESSMSG,EMPTY);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CurrentUserDetails cuser = (CurrentUserDetails) auth.getPrincipal();
		String email = cuser.getUsername();
    	String password = cuser.getPassword();
    	session.setAttribute(EMAIL,email);
    	session.setAttribute("password",password);
    	Set<String> roles = AuthorityUtils.authorityListToSet(auth.getAuthorities());
		if (roles.contains("ROLE_ADMIN")) {
			String i = "\n****Its a admin whose email="+email+" password="+password+" roles="+roles+"****\n";
        	LOGGER.info(i);
            return "redirect:/admin/backofficehome";
        }
        else{
        	String i = "\n****Its a user whose email="+email+" password="+password+" roles="+roles+"\n****";
        	LOGGER.info(i);
            return "redirect:/frontuser/uservalidate";
        }
	}
	
	@RequestMapping("frontuser/uservalidate")
	/*When the login form is submitted and the back office user validation fails its mapped here for front office user validation*/
	public String uservalidate(HttpSession session,Model m){
		String email = (String) session.getAttribute(EMAIL);
		String password = (String) session.getAttribute("password");
		session.setAttribute(SUCCESSMSG,EMPTY);
		LOGGER.info("\n\n****Logged in as Front Office User****\n");
		session.setAttribute("name",ulsi.getUsername(email));
		boolean status = ulsi.getStatus(email); //Checking the user is logging in for first time
		if(!status) 
			return "FrontOffice/frontofficehome"; //First time user are redirected to change password
		else{
				m.addAttribute(TRANSACTION,new Transaction());
				m.addAttribute(ACCOUNT,new Account());
				return USERHOME;
			}
	}
	
	
	/*When a request is made to logout and terminate the session its mapped here*/
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		LOGGER.info("\n****Logged out****");
		return "redirect:../login";
	}
	
	
	@GetMapping("/admin/backofficehome")
	public String loadBackOffice(Model m,HttpSession session) { 
		LOGGER.info("\n*****Redirecting to back office portal****\n");
		session.setAttribute(SUCCESSMSG,EMPTY);
		return "backofficehome";
	}
	
}
