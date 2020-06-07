package com.awards.api.exception;

public class AwardNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
		
	public AwardNotFoundException(String message) {
		super(message);
	}
}
