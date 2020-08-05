package com.mimacom.infrastructure.exception;

public class MicroserviceException extends RuntimeException {

	private static final long serialVersionUID = -8266143210484243563L;
	
	private final ThingsToDoErrorCode code;
	
	public MicroserviceException(ThingsToDoErrorCode code) {
		super(code.getMessage());
		this.code = code;
	}
	
	public MicroserviceException(ThingsToDoErrorCode code, Throwable cause) {
		super(code.getMessage(), cause);
		this.code = code;
	}
	
	public MicroserviceException(ThingsToDoErrorCode code, Throwable cause, String extraInfo) {
		super(code.getMessage() + " [" +extraInfo + "] " + cause.getMessage(), cause);
		this.code = code;
	}
	
	public MicroserviceException(String message, ThingsToDoErrorCode code) {
		super(message);
		this.code = code;
	}
	
	public MicroserviceException(String message, ThingsToDoErrorCode code, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ThingsToDoErrorCode getErrorCode() {
		return this.code;
	}
}
