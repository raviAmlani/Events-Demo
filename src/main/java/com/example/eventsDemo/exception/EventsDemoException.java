package com.example.eventsDemo.exception;

public class EventsDemoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EventsDemoException() {
	}

	public EventsDemoException(String message) {
		super(message);
	}

	public EventsDemoException(Throwable cause) {
		super(cause);
	}

	public EventsDemoException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventsDemoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
