package com.pack.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.pack.bank.service.AdminLoginServiceImpl;

@Profile("!test")
@Component
public class CommandLineTaskExecutor implements CommandLineRunner{
	
	@Autowired
	AdminLoginServiceImpl alsi;
	
	@Override
   public void run(String[] args) throws Exception
   {
       // Inserting the data in the backofficeuser table if empty.
		alsi.insertIfEmpty();  
   }

}
