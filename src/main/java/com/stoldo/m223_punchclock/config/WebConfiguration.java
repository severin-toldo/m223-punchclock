package com.stoldo.m223_punchclock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	@Value("${security.allowed-origins}")
	private String allowedOrigins;
	
    
	@Override
	public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedMethods("*")
            .allowedHeaders("*")
            .exposedHeaders(
                  "Access-Control-Allow-Headers",
                  "Access-Control-Allow-Origin",
                  "Access-Control-Expose-Headers",
                  "Authorization",
                  "Cache-Control",
                  "Content-Type",
                  "Origin"
    		)
            .allowedOrigins(allowedOrigins.split(","))
            .allowCredentials(true);
    }
}
