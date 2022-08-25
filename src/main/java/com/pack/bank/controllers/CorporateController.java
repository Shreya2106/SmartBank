package com.pack.bank.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pack.bank.dao.CorporateDao;
import com.pack.bank.model.Corporates;

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
	CorporateDao cdao;
	
/* ********CORPORATE SET UP******* */
	
	@RequestMapping("/corporate")
	public String loadCorporate(Model m) {
		m.addAttribute("corp", new  Corporates());  
		return "CorporateSetUp/corporate";
	}
	@RequestMapping("/corporateupdate")
	public String editCorp( @RequestParam("id") int ide, Model m){    
		 Corporates c = cdao.getCorpById(ide); //Fetching the data of the corporate for whom the fields are to be modified
		 m.addAttribute("editCorpForm",c);  
	      return "CorporateSetUp/editCorp";    
	 } 
	@RequestMapping("/editCorporate")
	public String modifyCorp(@ModelAttribute("editCorpForm") @Valid Corporates c,BindingResult br,HttpSession session,Model m,RedirectAttributes redirectAttributes) throws DataHandleException
	{
		if (br.hasErrors()) { //Checking whether the update form meets the validations
			session.setAttribute("cupdateerror",TRUE);
			return "CorporateSetUp/editCorp";
		}
		else {
			session.setAttribute("cupdateerror", FALSE);
			int res=cdao.modify(c); //status code is returned after the corporate table is modified
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
	
	@RequestMapping(value="/corporatedelete/{ide}")    
    public String deleteCorp(@PathVariable int ide,HttpSession session,Model m){    
	    int res=cdao.delete(ide); 
		if (res>=1){
			session.setAttribute(SUCCESSMSG,DELETESUCCESSMSG);
			return "redirect:../corporate";
		}
		else{
			Object obj = new Corporates();
			throw new DataHandleException(ERRORDELETEMSG,CORPERROR,obj,"corp");
		} 
    }
	@PostMapping(value = "/addCorporate") 
	  public String addCorporate(@ModelAttribute("corp") @Valid Corporates corp,BindingResult br,HttpSession session) {
		if (br.hasErrors()) {
			session.setAttribute("corperror", TRUE);
			return "CorporateSetUp/corporate";
		}
		else {
			session.setAttribute("corperror", FALSE);
			int res= cdao.insert(corp);
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
