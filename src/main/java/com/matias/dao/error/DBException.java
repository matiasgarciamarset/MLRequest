package com.matias.dao.error;

public class DBException extends Exception  {
	
	private static final long serialVersionUID = -5135529939384277754L;

	public DBException() {
		super();
	}
	
	public DBException(String message) {
		super(message);
	}
	
	public DBException(Throwable cause) {
		super(cause);
	}

}
