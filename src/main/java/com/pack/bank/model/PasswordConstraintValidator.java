package com.pack.bank.model;


import javax.validation.ConstraintValidator;  
import javax.validation.ConstraintValidatorContext;  
  
public class PasswordConstraintValidator implements ConstraintValidator<Password,String> {   
	
	String pwdpattern ="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"; 
    
	public boolean isValid(String pwd, ConstraintValidatorContext cvc) {     
      return pwd.matches(pwdpattern);	 
    }  

	@Override
	public void initialize(Password password) {
	password.message();
	}
}