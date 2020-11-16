package com.stoldo.m223_punchclock.config.exception_handler;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stoldo.m223_punchclock.model.api.ExceptionResponse;

/**
 * Catches all exceptions thrown in filters and handles them properly
 * */

public class FilterExceptionHandler extends OncePerRequestFilter implements ExceptionHandler {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);	
		} catch (Throwable t) {
			ExceptionResponse er = throwableToExceptionResponse(t);
			response.setStatus(er.getHttpStatusCode());
	    	response.getWriter().write(objectMapper.writeValueAsString(er));
	    	response.getWriter().flush();
	    	response.getWriter().close();
		}
	}

}
