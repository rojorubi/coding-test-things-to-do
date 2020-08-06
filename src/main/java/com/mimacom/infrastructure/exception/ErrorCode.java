package com.mimacom.infrastructure.exception;


import org.springframework.http.HttpStatus;

import java.io.Serializable;

public interface ErrorCode extends Serializable {

	public String getCode();
	public String getMessage();
	public HttpStatus getHttpStatus();

}