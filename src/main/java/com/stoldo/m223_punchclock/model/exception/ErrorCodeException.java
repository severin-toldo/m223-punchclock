package com.stoldo.m223_punchclock.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.stoldo.m223_punchclock.model.enums.ErrorCode;

public class ErrorCodeException extends ResponseStatusException {

	private static final long serialVersionUID = -7473748909684427371L;

	public ErrorCodeException(ErrorCode errorCode, HttpStatus status) {
		super(status, errorCode.getMessage());
	}
	
	public ErrorCodeException(ErrorCode errorCode, HttpStatus status, Throwable throwable) {
		super(status, errorCode.getMessage(), throwable);
	}

}
