package com.pack.bank.controllers;

public class DataHandleException extends RuntimeException
{
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
	@Override
	public String getMessage() {
		return message;
	}
}
