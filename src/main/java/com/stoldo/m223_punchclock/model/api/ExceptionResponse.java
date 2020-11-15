package com.stoldo.m223_punchclock.model.api;

import org.springframework.http.HttpStatus;

import com.stoldo.m223_punchclock.model.enums.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
	
	private HttpStatus httpStatus;
	private ErrorCode errorCode;
	private String message;

}
