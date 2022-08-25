package com.pack.bank.controllers;

public class LoginException extends RuntimeException
{
	private final String errorMsg;
	
	public LoginException(String message)
	{
		this.errorMsg=message;
	}

	@Override
	public String getMessage() {
		return errorMsg;
	}
}
