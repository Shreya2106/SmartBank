package com.pack.bank.exceptions;

public class ParamMissingException extends RuntimeException {
	private final String errorMsg;
	
	public ParamMissingException(String message)
	{
		this.errorMsg=message;
	}

	public String getErrorMessage() {
		return errorMsg;
	}
}
