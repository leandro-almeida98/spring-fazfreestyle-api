package veexi.fazfreestyle.api.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import veexi.fazfreestyle.api.security.TokenAuthenticationFilterSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	TokenAuthenticationFilterSecurity tokenAuthenticationFilterSecurity;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeHttpRequests().requestMatchers("/api/login/**", "/error", "/api/logout").permitAll().anyRequest()
				.authenticated().and()
				.addFilterBefore(tokenAuthenticationFilterSecurity, BasicAuthenticationFilter.class);
		return http.build();
	}

}
