package com.pack.bank.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pack.bank.model.Corporates;
import com.pack.bank.service.CorporateManageDataImpl;

@Controller
public class CorporateController {
	
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
	public static final String CORPERROR="CorporateSetUp/corporateError";
	
	@Autowired
	CorporateManageDataImpl cmdl;
	
	@RequestMapping("/corporate")
	public String loadCorporate(Model m) {
		m.addAttribute("corp", new  Corporates());  
		return "CorporateSetUp/corporate";
	}
	
	
	/*When a request to fetch update form for any particular corporate is made its mapped here*/
	@RequestMapping("/corporateupdate")
	public String editCorp( @RequestParam("id") int ide, Model m){    
		 Corporates c = cmdl.getCorpById(ide); //Fetching the data of the corporate for whom the fields are to be modified
		 m.addAttribute("editCorpForm",c);  
	      return "CorporateSetUp/editCorp";    
	 } 
	
	
	/*When the update form of the corporate is submitted its mapped here*/
	@RequestMapping("/editCorporate")
	public String modifyCorp(@ModelAttribute("editCorpForm") @Valid Corporates c,BindingResult br,HttpSession session,Model m,RedirectAttributes redirectAttributes) throws DataHandleException
	{
		if (br.hasErrors()) { //Checking whether the update form meets the server side validations
			session.setAttribute("cupdateerror",TRUE);
			return "CorporateSetUp/editCorp";
		}
		else {
			session.setAttribute("cupdateerror", FALSE);
			int res=cmdl.edit(c); //status code is returned after the corporate table is modified
			if (res>=1){
				session.setAttribute(SUCCESSMSG,UPDATESUCCESSMSG);
				m.addAttribute("corp", new  Corporates());
				return "redirect:corporate";
			}
			else{
				Object obj = new Corporates();
				throw new DataHandleException(ERRORUPDATEMSG,CORPERROR,obj,"corp"); //Custom exception is thrown if the table's isn't getting altered as per command
			}	
		  }  			 
	}
	
	
	/*When any particular corporate is soft-deleted its mapped here*/
	@GetMapping(value="/corporatedelete/{ide}")    
    public String deleteCorp(@PathVariable int ide,HttpSession session,Model m){    
	    int res=cmdl.delete(ide); 
		if (res>=1){
			session.setAttribute(SUCCESSMSG,DELETESUCCESSMSG);
			return "redirect:../corporate";
		}
		else{
			Object obj = new Corporates();
			throw new DataHandleException(ERRORDELETEMSG,CORPERROR,obj,"corp");
		} 
    }
	
	
	/*When corporate add  form is submitted its mapped here*/
	@PostMapping(value = "/addCorporate") 
	  public String addCorporate(@ModelAttribute("corp") @Valid Corporates corp,BindingResult br,HttpSession session) {
		if (br.hasErrors()) {  //Server side validations
			//If the add form contains error its redirected back with the error messages
			session.setAttribute("corperror", TRUE);
			return "CorporateSetUp/corporate";
		}
		else {
			session.setAttribute("corperror", FALSE);
			int res= cmdl.add(corp); //Corporate's data is sent to the add function of Corporate Dao
			 if (res>=1){
					session.setAttribute(SUCCESSMSG,ADDSUCCESSMSG);
					return "redirect:corporate";
				}
				else{
					Object obj = new Corporates();
					throw new DataHandleException(ERRORADDMSG,CORPERROR,obj,"corp");
				} 
		  }
	}
}
