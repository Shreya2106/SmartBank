package com.pack.bank.model;

import javax.validation.ConstraintValidator;  
import javax.validation.ConstraintValidatorContext; 

public class AddressConstraintValidator implements ConstraintValidator<Address,String>{
String addresspattern ="(?=.*[a-z])(?=.*[A-Z]).{5,}"; 
    
	public boolean isValid(String add, ConstraintValidatorContext cvc) {     
      return add.matches(addresspattern);	
    }  

	@Override
	public void initialize(Address address) {
	address.message();
	}
}
