package com.pack.bank.controllers;

public class NoSuchUserException extends RuntimeException
	{
		private final String errorMsg;
		
		public NoSuchUserException(String message)
		{
			this.errorMsg=message;
		}

		@Override
		public String getMessage() {
			return errorMsg;
		}

}
