package com.matias.service.error;

public class ServiceException extends Exception  {
	
	private static final long serialVersionUID = 5109914284500443125L;

	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}

}
