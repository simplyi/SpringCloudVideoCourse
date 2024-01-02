package com.appsdeveloperblog.photoapp.discovery;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http.csrf((csrf) -> csrf.disable());
		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}
}
