package com.stoldo.m223_punchclock.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stoldo.m223_punchclock.model.api.UserLoginRequest;
import com.stoldo.m223_punchclock.model.entity.UserEntity;
import com.stoldo.m223_punchclock.model.enums.ErrorCode;
import com.stoldo.m223_punchclock.model.exception.ErrorCodeException;
import com.stoldo.m223_punchclock.service.UserEntityService;
import com.stoldo.m223_punchclock.shared.Constants;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
	private String jwtSecret;
	private Integer jwtTokenValidityInMinutes;
	private UserEntityService userEntityService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, String jwtSecret, Integer jwtTokenValidityInMinutes, UserEntityService userEntityService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtSecret = jwtSecret;
		this.jwtTokenValidityInMinutes = jwtTokenValidityInMinutes;
		this.userEntityService = userEntityService;
	}

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
        	UserLoginRequest ulr = objectMapper.readValue(req.getInputStream(), UserLoginRequest.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(ulr.getEmail(), ulr.getPassword(), Collections.emptyList()));
        } catch (IOException ex) {
        	throw new ErrorCodeException(ErrorCode.E1001, HttpStatus.BAD_REQUEST, ex);
        }
    }
    
    @Override
    public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
    	User user = (User) auth.getPrincipal();
    	UserEntity ue = userEntityService.getByEmail(user.getUsername());
        
        String token = JWT
        		.create()
        		.withSubject(ue.getEmail())
                .withExpiresAt(DateUtils.addMinutes(new Date(), jwtTokenValidityInMinutes))
                .sign(Algorithm.HMAC256(jwtSecret.getBytes()));

		res.addHeader(Constants.AUTH_HEADER_NAME, Constants.JWT_TOKEN_PREFIX + token);
		res.getWriter().write(objectMapper.writeValueAsString(ue));
		res.getWriter().flush();
		res.getWriter().close();
    }
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException ae) throws IOException, ServletException {
        throw new ErrorCodeException(ErrorCode.E1007, HttpStatus.BAD_REQUEST, ae);
    }
}
