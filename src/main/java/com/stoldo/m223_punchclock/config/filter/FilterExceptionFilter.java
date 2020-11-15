package com.stoldo.m223_punchclock.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stoldo.m223_punchclock.model.api.ExceptionResponse;
import com.stoldo.m223_punchclock.service.ExceptionHandlerService;

/**
 * Catches all exceptions thrown in filters and propagates them to the ExceptionHandlerService
 * */

public class FilterExceptionFilter extends OncePerRequestFilter {
	
	private ExceptionHandlerService exceptionHandlerService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	public FilterExceptionFilter(ExceptionHandlerService exceptionHandlerService) {
		this.exceptionHandlerService = exceptionHandlerService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);	
		} catch (Throwable t) {
			ExceptionResponse er = exceptionHandlerService.handle(t);
			response.setStatus(er.getHttpStatusCode());
	    	response.getWriter().write(objectMapper.writeValueAsString(er));
	    	response.getWriter().flush();
	    	response.getWriter().close();
		}
	}

}
