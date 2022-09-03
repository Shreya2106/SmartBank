package com.pack.bank.exceptions;

import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pack.bank.model.Account;
import com.pack.bank.model.Transaction;

@ControllerAdvice
public class GlobalException  extends RuntimeException implements ErrorController{
	//ErrorController is implemented so as to map server errors to loginerrors.jsp
	//Since all the exceptions handled are likely to occur at runtime so the class extends runtime exception
	
	public static final String ERRORMSG="errorMsg";
	public static final String LOGINERRORS="loginerrors";
	
	
	/*When mandatory parameter missing exception is thrown its mapped here*/
	@ExceptionHandler(ParamMissingException.class)
	public String handleParamMissing(ParamMissingException ud,HttpSession session) {
		session.setAttribute(ERRORMSG,ud.getErrorMessage());
		return LOGINERRORS;
    }
	
	/*When any data handle exception is thrown its mapped here*/
	@ExceptionHandler(DataHandleException.class)
	public String processUpdateException(DataHandleException ud,Model m,HttpSession session)
	{
		 m.addAttribute(ud.getAttrName(), ud.getObj());
		 session.setAttribute(ERRORMSG, ud.getErrorMessage());
	     return ud.getPath();
	}	
	
	/*When any transaction handle exception is thrown its mapped here*/
	@ExceptionHandler(TransHandleException.class)
	public String processTransException(TransHandleException ud,Model m,HttpSession session)
	{
		 m.addAttribute("transaction",new Transaction());
		 m.addAttribute("account", new Account());
		 session.setAttribute(ERRORMSG, ud.getErrorMessage());
	     return "redirect:userhomes";
	}	
}
