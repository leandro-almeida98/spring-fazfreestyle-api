package veexi.fazfreestyle.api.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

import veexi.fazfreestyle.api.security.TokenAuthenticationFilterSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	TokenAuthenticationFilterSecurity tokenAuthenticationFilterSecurity;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // permitir
																													// acesso
																													// a
																													// recursos
																													// estáticos
																													// comuns
																													// do
																													// Spring
																													// Boot
																													// (como
																													// CSS
																													// e
																													// JS)
				.requestMatchers(request -> request.getRequestURI().startsWith("/api/")).authenticated() // solicitar
																											// autenticação
																											// para
																											// rotas que
																											// começam
																											// com /api/
				.and().addFilterAfter(tokenAuthenticationFilterSecurity, SecurityContextHolderAwareRequestFilter.class);
		return http.build();
	}

}
