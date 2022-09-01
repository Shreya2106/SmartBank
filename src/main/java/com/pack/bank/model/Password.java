package com.pack.bank.model;

import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
import javax.validation.Constraint;  
import javax.validation.Payload;  
  
@Constraint(validatedBy = PasswordConstraintValidator.class)  
@Target( { ElementType.FIELD } )  
@Retention(RetentionPolicy.RUNTIME)  
public @interface Password {  
    //error message  
    public String message() default "must contain reqd format";  
	
	  //represents group of constraints 
 Class<?>[] groups() default {};
	  //represents additional information about annotation 
 public Class<? extends
	  Payload>[] payload() default {};
	 
}  