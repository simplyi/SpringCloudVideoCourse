package com.appsdeveloperblog.photoapp.api.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.appsdeveloperblog.photoapp.api.users.service.UsersService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurity {

	private Environment environment;
	private UsersService usersService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public WebSecurity(Environment environment, UsersService usersService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.environment = environment;
		this.usersService = usersService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {

		// Configure AuthenticationManagerBuilder
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);

		// Get AuthenticationManager
		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

		http.csrf().disable();
		http.authorizeHttpRequests()

				.requestMatchers(HttpMethod.GET, "/actuator/health").permitAll()
				.requestMatchers(HttpMethod.GET, "/actuator/circuitbreakerevents").permitAll().and()
				.addFilter(getAuthenticationFilter(authenticationManager))
				.authenticationManager(authenticationManager);

		http.headers().frameOptions().disable();

		return http.build();
	}

	AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(usersService,environment, authenticationManager);
        filter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
        return filter;
    }

 

}
