package com.stoldo.m223_punchclock.config.exception_handler;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import com.stoldo.m223_punchclock.model.api.ExceptionResponse;
import com.stoldo.m223_punchclock.model.enums.ErrorCode;
import com.stoldo.m223_punchclock.model.exception.ErrorCodeException;


public interface ExceptionHandler {
	
	public default ExceptionResponse throwableToExceptionResponse(Throwable t) {
		t.printStackTrace();
		
		if (t instanceof ErrorCodeException) {
			ErrorCodeException ece = (ErrorCodeException) t;
			return new ExceptionResponse(ece.getStatus(), ece.getErrorCode(), ece.getMessage());
		} else if (t instanceof ResponseStatusException) {
			ResponseStatusException rse = (ResponseStatusException) t;
			return new ExceptionResponse(rse.getStatus(), ErrorCode.E1000, rse.getMessage());
		} else if (t instanceof EntityNotFoundException) {
			EntityNotFoundException enfe = (EntityNotFoundException) t;
			return new ExceptionResponse(HttpStatus.BAD_REQUEST, ErrorCode.E1006, enfe.getMessage());
		} else if (t instanceof MethodArgumentNotValidException) {
			// TODO find better /cleaner / nicer solution
			MethodArgumentNotValidException manve = (MethodArgumentNotValidException) t;
			ErrorCode errorCode = null;
			
			for (ObjectError oe : manve.getBindingResult().getAllErrors()) {
				try {
					errorCode = ErrorCode.valueOf(oe.getDefaultMessage());
				} catch (Exception e) {
				}	
			}
			
			if (errorCode == null) {
				for (FieldError fe : manve.getBindingResult().getFieldErrors()) {
					try {
						errorCode = ErrorCode.valueOf(fe.getDefaultMessage());
					} catch (Exception e) {
					}	
				}
			}
			
			if (errorCode != null) {
				return new ExceptionResponse(HttpStatus.BAD_REQUEST, errorCode, t.getMessage());	
			}
			
			return new ExceptionResponse(HttpStatus.BAD_REQUEST, ErrorCode.E1000, t.getMessage());
		} else {
			return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E1000, t.getMessage());
		}
	}

}
