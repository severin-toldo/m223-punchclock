package com.stoldo.m223_punchclock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stoldo.m223_punchclock.model.api.ExceptionResponse;
import com.stoldo.m223_punchclock.service.ExceptionHandlerService;

/**
 * Catches all exceptions thrown in Controllers and propagates them to the ExceptionHandlerService
 * */

@ControllerAdvice
public class ControllerExceptionHandler {
	
	private ExceptionHandlerService exceptionHandlerService;
	
	
	@Autowired
	public ControllerExceptionHandler(ExceptionHandlerService exceptionHandlerService) {
		this.exceptionHandlerService = exceptionHandlerService;
	}
	
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handle(Throwable throwable) {
		ExceptionResponse er = exceptionHandlerService.handle(throwable);
    	
        return ResponseEntity
        		.status(er.getHttpStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(er);
    }
	
}
