package com.stoldo.m223_punchclock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.stoldo.m223_punchclock.config.filter.JwtAuthorizationFilter;
import com.stoldo.m223_punchclock.config.filter.FilterExceptionFilter;
import com.stoldo.m223_punchclock.config.filter.JwtAuthenticationFilter;
import com.stoldo.m223_punchclock.config.filter.RequestLogFilter;
import com.stoldo.m223_punchclock.service.ExceptionHandlerService;
import com.stoldo.m223_punchclock.service.UserEntityService;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Value("${security.jwt.secret}")
	private String jwtSecret;
	
	@Value("${security.jwt.token-validity-in-minutes}")
	private Integer jwtTokenValidityInMinutes;
	
	private UserEntityService userEntityService;
    private BCryptPasswordEncoder passwordEncoder;
    private ExceptionHandlerService exceptionHandlerService;
    
	
	public WebSecurityConfiguration(UserEntityService userEntityService, BCryptPasswordEncoder passwordEncoder, ExceptionHandlerService exceptionHandlerService) {
		this.userEntityService = userEntityService;
		this.passwordEncoder = passwordEncoder;
		this.exceptionHandlerService = exceptionHandlerService;
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.headers().frameOptions().sameOrigin();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/v3/**").permitAll()
			.antMatchers("/swagger-ui/**").permitAll()
			.antMatchers("/swagger-ui.html").permitAll()
			.antMatchers("/favicon.ico").permitAll()
			.anyRequest().authenticated();

		http
			.addFilterBefore(new RequestLogFilter(), BasicAuthenticationFilter.class)
			.addFilterBefore(new FilterExceptionFilter(exceptionHandlerService), RequestLogFilter.class)
			.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtSecret, jwtTokenValidityInMinutes, userEntityService))
			.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtSecret, userEntityService));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth
    		.userDetailsService((email) -> userEntityService.getByEmail(email).toUserDetails())
    		.passwordEncoder(passwordEncoder);
    }
}
