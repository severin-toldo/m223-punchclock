package com.stoldo.m223_punchclock.config.exception_handler;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stoldo.m223_punchclock.model.api.ExceptionResponse;


@ControllerAdvice
public class ControllerExceptionHandler implements com.stoldo.m223_punchclock.config.exception_handler.ExceptionHandler {
	
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionResponse> handle(Throwable throwable) {
		ExceptionResponse er = throwableToExceptionResponse(throwable);
    	
        return ResponseEntity
        		.status(er.getHttpStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(er);
    }
	
}
