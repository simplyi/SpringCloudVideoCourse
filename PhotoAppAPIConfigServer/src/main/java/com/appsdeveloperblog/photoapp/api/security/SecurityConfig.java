package com.appsdeveloperblog.photoapp.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	private Environment environment; 
	
	public SecurityConfig(Environment environment) {
		this.environment = environment;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		.authorizeHttpRequests(auth->auth
				.requestMatchers(HttpMethod.POST, "/actuator/busrefresh").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/encrypt").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/decrypt").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/**").hasRole("CLIENT")
				.anyRequest().authenticated())
		.csrf(csrf->csrf.ignoringRequestMatchers("/actuator/busrefresh","/encrypt","/decrypt"))
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	
	@Bean
	InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
		
		UserDetails admin = User
				.withUsername(environment.getProperty("spring.security.user.name"))
				.password(passwordEncoder.encode(environment.getProperty("spring.security.user.password")))
				.roles(environment.getProperty("spring.security.user.roles"))
				.build();
		
		UserDetails client = User
				.withUsername(environment.getProperty("my-spring.security.user.name"))
				.password(passwordEncoder.encode(environment.getProperty("my-spring.security.user.password")))
				.roles(environment.getProperty("my-spring.security.user.roles"))
				.build();
		
		
		
		return new InMemoryUserDetailsManager(admin,client);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
