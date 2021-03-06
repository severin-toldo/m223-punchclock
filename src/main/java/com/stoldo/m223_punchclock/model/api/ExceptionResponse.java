package com.stoldo.m223_punchclock.model.api;

import org.springframework.http.HttpStatus;

import com.stoldo.m223_punchclock.model.enums.ErrorCode;

import lombok.Getter;


@Getter
public class ExceptionResponse {
	
	private HttpStatus httpStatus;
	private int httpStatusCode;
	private ErrorCode errorCode;
	private String message;
	
	
	public ExceptionResponse(HttpStatus httpStatus, ErrorCode errorCode, String message) {
		super();
		this.httpStatus = httpStatus;
		this.httpStatusCode = httpStatus.value();
		this.errorCode = errorCode;
		this.message = message;
	}
}
