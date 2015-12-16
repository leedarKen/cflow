package com.csoft.resource.cflow.exception;

public class ProcessException extends Exception {

	private String message;

	public ProcessException(String message) {
		super(message);
		this.message = message;
	}
	public ProcessException() {
		super();
	}

	public ProcessException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProcessException(Throwable cause) {
		super(cause);
	}
}
