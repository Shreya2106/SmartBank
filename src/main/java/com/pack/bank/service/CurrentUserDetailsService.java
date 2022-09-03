package com.pack.bank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pack.bank.exceptions.ParamMissingException;
import com.pack.bank.model.BackOfficeUser;
import com.pack.bank.model.User;
import com.pack.bank.repository.BackOfficeUserRepository;
import com.pack.bank.repository.UserRepository;

@Service
public class CurrentUserDetailsService implements UserDetailsService  {
	@Autowired
    private UserLoginServiceImpl userServices; //Custom User Login Service
    @Autowired
    private AdminLoginServiceImpl adminService;

    private static final Logger LOGGER=LoggerFactory.getLogger(CurrentUserDetailsService.class);
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        
    	if(username==null || username.equals(""))
    		throw new UsernameNotFoundException("*****Mandatory Parameters missing");
    	// first try loading from User table
    	User user = userServices.getUser(username);
        if (user != null) {
        	LOGGER.info("\n****The user exists with the given email****\n");
            return new CurrentUserDetails(user.getLoginId(), user.getPassword(), user.getRole());
        } else {
            // Not found in user table, so check admin
            BackOfficeUser admin = adminService.getUser(username);
            if (admin != null) {
            	LOGGER.info("\n*****The admin exists with the given email****\n");
                return new CurrentUserDetails(admin.getLoginId(), admin.getPassword(), admin.getRole());
            }
        }
        LOGGER.info("\n*****No such user*****");
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}