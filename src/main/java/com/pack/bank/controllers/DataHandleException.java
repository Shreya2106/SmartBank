package com.pack.bank.controllers;

public class DataHandleException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6800056082766472376L;
	private final String message;
	private final String path;
	private final transient Object obj;
	private final String attrName;

	public String getAttrName() {
		return attrName;
	}

	public String getPath() {
		return path;
	}

	public DataHandleException(String message, String path,Object obj,String attrName)
	{
		this.message=message;
		this.path=path;
		this.obj = obj;
		this.attrName=attrName;
	}

	public Object getObj() {
		return obj;
	}

	public String getErrorMessage() {
		return message;
	}
}
