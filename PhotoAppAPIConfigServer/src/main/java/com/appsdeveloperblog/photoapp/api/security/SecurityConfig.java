package com.appsdeveloperblog.photoapp.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		.authorizeHttpRequests(auth->auth.anyRequest().authenticated())
		.csrf(csrf->csrf.ignoringRequestMatchers("/actuator/busrefresh"))
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}

}
