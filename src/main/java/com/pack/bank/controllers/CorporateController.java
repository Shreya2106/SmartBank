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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pack.bank.exceptions.DataHandleException;
import com.pack.bank.model.Corporates;
import com.pack.bank.service.CorporateManageDataImpl;

@RequestMapping("/admin")
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
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CorporateController.class);
	
	@Autowired
	CorporateManageDataImpl cmdl;
	
	@RequestMapping("/corporates")
    public ModelAndView findAllCorp(ModelAndView mav,Model m,HttpSession session){
        List<Corporates> result = cmdl.viewAll();
        mav.addObject("result", result);
        m.addAttribute("corp", new  Corporates()); 
        mav.setViewName("CorporateSetUp/corporate");
        return mav;
    }
	
	
	/*When a request to fetch update form for any particular corporate is made its mapped here*/
	@RequestMapping("/corporateupdate")
	public String editCorp( @RequestParam("id") int ide, Model m,HttpSession session){    
		  Corporates c = cmdl.getCorpById(ide); //Fetching the data of the corporate for whom the fields are to be modified
		  m.addAttribute("editCorpForm",c); 
		  session.setAttribute("id",ide);
	      return "CorporateSetUp/editCorp";    
	 } 
	
	
	/*When the update form of the corporate is submitted its mapped here*/
	@RequestMapping("/editCorporate")
	public String modifyCorp(@ModelAttribute("editCorpForm") @Valid Corporates c,BindingResult br,HttpSession session,Model m,RedirectAttributes redirectAttributes) throws DataHandleException
	{
		int ide = c.getCorporateId();
		if (br.hasErrors()) { //Checking whether the update form meets the server side validations
			session.setAttribute("cupdateerror",TRUE);
			session.setAttribute("id",ide);
			LOGGER.info("\n\n****Oops there was some server side validation error in  corporate update form****\n");
			return "CorporateSetUp/editCorp";
		}
		else {
			session.setAttribute("cupdateerror", FALSE);
			cmdl.edit(c); //status code is returned after the corporate table is modified
			session.setAttribute(SUCCESSMSG,UPDATESUCCESSMSG);
			m.addAttribute("corp", new  Corporates());
			LOGGER.info("\n\n****Corporate updated successfully****\n");
			return "redirect:../admin/corporates";
		  }			 
	}
	
	
	/*When any particular corporate is soft-deleted its mapped here*/
	@GetMapping(value="/corporatedelete/{ide}")    
    public String deleteCorp(@PathVariable int ide,HttpSession session,Model m){    
	    int res = cmdl.delete(ide); 
		if (res>=1){
			session.setAttribute(SUCCESSMSG,DELETESUCCESSMSG);
			LOGGER.info("\n\n****Corporates deleted successfully****\n");
			return "redirect:../../admin/corporates";
		}
		else{
			Object obj = new Corporates();
			LOGGER.info("\n\n****Oops there was some error in  corporate delete****\n");
			throw new DataHandleException(ERRORDELETEMSG,CORPERROR,obj,"corp");
		} 
    }
	
	
	/*When corporate add  form is submitted its mapped here*/
	@PostMapping(value = "/addCorporate") 
	  public String addCorporate(@ModelAttribute("corp") @Valid Corporates corp,BindingResult br,HttpSession session,Model m) {
		if (br.hasErrors()) {  //Server side validations
			//If the add form contains error its redirected back with the error messages
			session.setAttribute("corperror", TRUE);
			 List<Corporates> result = cmdl.viewAll();
		     m.addAttribute("result", result);
		     LOGGER.info("\n\n****Oops there was some server side validation error in  corpoarte add form****\n");
			return "CorporateSetUp/corporate";
		}
		else {
			session.setAttribute("corperror", FALSE);
			cmdl.add(corp); //Corporate's data is sent to the add function of Corporate Dao
			session.setAttribute(SUCCESSMSG,ADDSUCCESSMSG);
			LOGGER.info("\n\n****Corporates added successfully****\n");
			return "redirect:../admin/corporates";
			}
	}
}
