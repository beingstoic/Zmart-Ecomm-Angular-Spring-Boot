package com.cg.feedback.exception;

public class BadRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5605169750720449391L;
	
	private static String defaultMessage = "Bad Request";

	public BadRequestException() {
		super(defaultMessage);
	}

	public BadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
