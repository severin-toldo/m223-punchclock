package com.stoldo.m223_punchclock.config.exception_handler;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
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
		} else {
			return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E1000, t.getMessage());
		}
	}

}
