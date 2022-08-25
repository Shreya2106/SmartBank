package com.pack.bank.model;

import javax.validation.ConstraintValidator;  
import javax.validation.ConstraintValidatorContext;  
  
public class ContactConstraintValidator implements ConstraintValidator<Contact,String> {   
	
	String phpattern ="[6-9]{1}[0-9]{9}"; 
    
	public boolean isValid(String ph, ConstraintValidatorContext cvc) {     
		return ph.matches(phpattern);
    }  

	@Override
	public void initialize(Contact contact) {
	contact.message();
	}
}