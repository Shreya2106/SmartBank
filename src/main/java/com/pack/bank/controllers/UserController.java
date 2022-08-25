package com.pack.bank.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.bank.dao.UserDao;
import com.pack.bank.model.User;

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
	
	@Autowired
	UserDao udao;
	
/* ********USER SET UP******* */
	
	@RequestMapping("/user")
	public String loadUser(Model m) {
		m.addAttribute("user", new  User());  
		return "UserSetUp/user";
	}
	@RequestMapping("/userupdate")
	public String editUser( @RequestParam("id") String ide, Model m){    
		 User u = udao.getUserById(ide);
		 m.addAttribute("editUserForm",u); 
	      return "UserSetUp/editUser";    
	 } 
	@RequestMapping("/editUser")
	public String modifyUser(@ModelAttribute("editUserForm") @Valid User u,BindingResult br,HttpSession session,Model m)
	{
		if (br.hasErrors()) {
			session.setAttribute("updateerror", TRUE);
			return "UserSetUp/editUser";
		}
		else {
			session.setAttribute("updateerror", FALSE);
			int res=udao.modify(u);
			if (res>=1){
				session.setAttribute(SUCCESSMSG,UPDATESUCCESSMSG);
				m.addAttribute("user", new  User());
				return USER;
			}
			else{
				Object obj = new User();
				throw new DataHandleException(ERRORUPDATEMSG,USERERROR,obj,"user");
			}
		}
	}
	
	@RequestMapping(value="/userdelete")    
    public String deleteUser(@RequestParam String id,HttpSession session,Model m){  
	    int res=udao.delete(id); 
		if (res>=1){
			session.setAttribute(SUCCESSMSG,DELETESUCCESSMSG);
			m.addAttribute("user", new  User());
			return USER;
		}
		else{
			Object obj = new User();
			throw new DataHandleException(ERRORDELETEMSG,USERERROR,obj,"user");
		} 
    }
	@PostMapping(value = "/addUser") 
	  public String addUser(@ModelAttribute("user") @Valid User u,BindingResult br,HttpSession session) {
		if (br.hasErrors()) {
			session.setAttribute("usererror", TRUE);
			return "UserSetUp/user";
		}
		else {
			session.setAttribute("usererror", FALSE);
			int res= udao.insert(u);
			 if (res>=1){
					session.setAttribute(SUCCESSMSG,ADDSUCCESSMSG);
					return USER;
				}
				else{
					Object obj = new User();
					throw new DataHandleException(ERRORADDMSG,USERERROR,obj,"user");
				} 
		  }

	  }

}
