package com.pack.bank.exceptions;

public class TransHandleException extends RuntimeException{
	/**
		 * 
		 */
		private static final long serialVersionUID = 6800056082766472376L;
		private final String message;
		private final String path;
		private final transient Object obj1;
		private final transient Object obj2;
		private final String attrName1;
		private final String attrName2;

		public String getAttrName1() {
			return attrName1;
		}
		
		public String getAttrName2() {
			return attrName2;
		}

		public String getPath() {
			return path;
		}

		public TransHandleException(String message, String path, Object obj1, Object obj2, String attrName1,String attrName2)
		{
			this.message=message;
			this.path=path;
			this.obj1 = obj1;
			this.obj2 = obj2;
			this.attrName1=attrName1;
			this.attrName2=attrName2;
		}

		public Object getObj1() {
			return obj1;
		}
		
		public Object getObj2() {
			return obj2;
		}

		public String getErrorMessage() {
			return message;
		}

}
