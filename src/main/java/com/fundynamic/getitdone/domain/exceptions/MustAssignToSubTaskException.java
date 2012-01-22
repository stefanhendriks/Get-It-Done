package com.fundynamic.getitdone.domain.exceptions;

public class MustAssignToSubTaskException extends RuntimeException {

	public MustAssignToSubTaskException() {
		super();
	}

	public MustAssignToSubTaskException(String message, Throwable cause) {
		super(message, cause);
	}

	public MustAssignToSubTaskException(String message) {
		super(message);
	}

	public MustAssignToSubTaskException(Throwable cause) {
		super(cause);
	}
	
}
