package com.pack.bank.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pack.bank.model.User;
import com.pack.bank.service.CorporateManageDataImpl;
import com.pack.bank.service.UserManageDataImpl;


@Controller
public class UserController {
	
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
	public static final String USER="redirect:user";
	public static final String USERERROR="UserSetUp/userError";
	public static final String USERSETUP="UserSetUp/user";
	private static final String RESULT1 = "result1";
	
	private static final Logger LOGGER=LoggerFactory.getLogger(UserController.class);
	
	
	@Autowired
	UserManageDataImpl umdl;
	
	@Autowired
	CorporateManageDataImpl cmdl;
	
	@RequestMapping("/user")
	 public ModelAndView findAllUsers(ModelAndView mav,Model m,HttpSession session){
        List<User> result = umdl.viewAll();
        List<Integer> result1 = cmdl.distinctId();
        mav.addObject("result", result);
        mav.addObject(RESULT1, result1);
        session.setAttribute(RESULT1, result1);
        m.addAttribute("user", new  User());
        mav.setViewName(USERSETUP);
        return mav;
    }
	
	/*When a request to fetch update form for any particular user is made its mapped here*/
	@RequestMapping("/userupdate")
	public String editUser( @RequestParam("id") String ide, Model m,HttpSession session){    
		 User u = umdl.getUserByEmail(ide);
		 m.addAttribute("editUserForm",u); 
		 session.setAttribute("id",ide);
	      return "UserSetUp/editUser";    
	 } 
	
	
	/*When the update form of the user is submitted its mapped here*/
	@RequestMapping("/editUserSub")
	public String modifyUser(@ModelAttribute("editUserForm") @Valid User u,BindingResult br,HttpSession session,Model m)
	{
		String ide = u.getLoginId();
		if (br.hasErrors()) {
			session.setAttribute("updateerror", TRUE);
			session.setAttribute("id",ide);
			LOGGER.info("\n\n****Oops there was some server side validation error in  user update form****\n");
			return "UserSetUp/editUser";
		}
		else {
			session.setAttribute("updateerror", FALSE);
			int res = umdl.edit(u);
			if (res>=1){
				session.setAttribute(SUCCESSMSG,UPDATESUCCESSMSG);
				m.addAttribute("user", new  User());
				LOGGER.info("\n\n****User updated successfully****\n");
				return USER;
			}
			else{
				Object obj = new User();
				LOGGER.info("\n\n****Oops there was some error in  user update****\n");
				throw new DataHandleException(ERRORUPDATEMSG,USERERROR,obj,"user");
			}
		}
	}
	/*When any particular user is deleted its mapped here*/
	@RequestMapping(value="/userdelete")    
    public String deleteUser(@RequestParam String id,HttpSession session,Model m){  
	    int res = umdl.delete(id); 
		if (res>=1){
			session.setAttribute(SUCCESSMSG,DELETESUCCESSMSG);
			m.addAttribute("user", new  User());
			LOGGER.info("\n\n****User deleted successfully****\n");
			return USER;
		}
		else{
			Object obj = new User();
			LOGGER.info("\n\n****Oops there was some error in  user delete****\n");
			throw new DataHandleException(ERRORDELETEMSG,USERERROR,obj,"user");
		} 
    }
	
	
	/*When user add  form is submitted its mapped here*/
	@PostMapping(value = "/addUser") 
	  public String addUser(@ModelAttribute("user") @Valid User u,BindingResult br,HttpSession session,Model m) {
		if (br.hasErrors()) { //Server side validations
			//If the add form contains error its redirected back with the error messages
			session.setAttribute("usererror", TRUE);
			List<User> result = umdl.viewAll();
	        List<Integer> result1 = cmdl.distinctId();
	        m.addAttribute("result", result);
	        m.addAttribute(RESULT1, result1);
	        LOGGER.info("\n\n****Oops there was some server side validation error in  user add form****\n");
			return USERSETUP;
		}
		else {
			session.setAttribute("usererror", FALSE);
			u.setPassword("userC123##");
			umdl.add(u); //User's data is sent to the add function of User Dao
			session.setAttribute(SUCCESSMSG,ADDSUCCESSMSG);
			LOGGER.info("\n\n****User added successfully****\n");
			return USER;
		  }

	  }
	

}
