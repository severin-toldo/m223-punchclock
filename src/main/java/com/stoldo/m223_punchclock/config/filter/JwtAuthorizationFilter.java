package com.stoldo.m223_punchclock.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.stoldo.m223_punchclock.model.enums.ErrorCode;
import com.stoldo.m223_punchclock.model.exception.ErrorCodeException;
import com.stoldo.m223_punchclock.service.UserEntityService;
import com.stoldo.m223_punchclock.shared.Constants;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private String jwtSecret;
	private UserEntityService userEntityService;

	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, String jwtSecret, UserEntityService userEntityService) {
		super(authenticationManager);
		this.jwtSecret = jwtSecret;
		this.userEntityService = userEntityService;
	}

	@Override
	public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		String token = req.getHeader(Constants.AUTH_HEADER_NAME);
	
		if (!StringUtils.startsWith(token, Constants.JWT_TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		String email = null;
		String token = req.getHeader(Constants.AUTH_HEADER_NAME);
	
		try {
			email = JWT
					.require(Algorithm.HMAC256(jwtSecret.getBytes()))
					.build()
					.verify(token.replace(Constants.JWT_TOKEN_PREFIX, ""))
					.getSubject();	
		} catch (TokenExpiredException tee) {
			throw new ErrorCodeException(ErrorCode.E1005, HttpStatus.UNAUTHORIZED);
		}
		
		if (email != null) {
			UserDetails userDetail = userEntityService.getByEmail(email).toUserDetails();
			return new UsernamePasswordAuthenticationToken(email, null, userDetail.getAuthorities());
		}
		
		return null;
	}
}
