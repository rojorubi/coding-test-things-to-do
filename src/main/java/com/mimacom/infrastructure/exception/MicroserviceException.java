package com.mimacom.infrastructure.exception;

public class MicroserviceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final ErrorCode code;

	public MicroserviceException(ThingsToDoErrorCode code) {
		super(code.getCode()+" "+ code.getMessage());
		this.code = code;
	}
	
	public MicroserviceException(ThingsToDoErrorCode code, String description) {
		super(description);
		this.code = code;
	}
	
	public MicroserviceException(ThingsToDoErrorCode code, Throwable cause) {
		super(code.getMessage() + " [" +cause.getMessage() + "]", cause);
		this.code = code;
	}
	
	public MicroserviceException(ThingsToDoErrorCode code, Throwable cause, String description) {
		super(description + " [" +cause.getMessage() + "]", cause);
		this.code = code;
	}
}
