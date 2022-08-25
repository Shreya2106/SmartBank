package com.pack.bank.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalException extends RuntimeException {
	@ExceptionHandler(LoginException.class)
	public ModelAndView  processLoginException(LoginException ud)
	{
		ModelAndView mav = new ModelAndView("loginerrors");
	     mav.addObject("errorMsg", ud.getMessage());
	     return mav;
	}
	@ExceptionHandler(DataHandleException.class)
	public ModelAndView  processUpdateException(DataHandleException ud)
	{
		ModelAndView mav = new ModelAndView(ud.getPath());
		mav.addObject(ud.getAttrName(), ud.getObj());
		 mav.addObject("errorMsg", ud.getMessage());
	     return mav;
	}
}
