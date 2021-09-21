package com.cg.feedback.exception;

public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5165976759912324389L;
	
	private static String defaultMessage = "Resource not found";

	public NotFoundException() {
		super(defaultMessage);
	}
	
	public NotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
