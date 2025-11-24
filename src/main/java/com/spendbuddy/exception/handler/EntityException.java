package com.spendbuddy.exception.handler;

public class EntityException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityException(String message) {
		super(message);
	}

	public EntityException(Throwable cause) {
		super(cause);
	}
}
