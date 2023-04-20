package veexi.fazfreestyle.api.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import veexi.fazfreestyle.api.exceptions.ErrorResponse;
import veexi.fazfreestyle.api.exceptions.handle.ForbiddenException;

@Component
public class TokenAuthenticationFilterSecurity extends OncePerRequestFilter {

	@Autowired
	private JwtSecurity JwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = getTokenFromRequest(request);

		try {
			if (token != null && JwtUtil.validarToken(token)) {
				Authentication authentication = new UsernamePasswordAuthenticationToken(token, null,
						Collections.emptyList());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (ForbiddenException e) {
			handleForbiddenException(e, request, response);
			return;
		}

		filterChain.doFilter(request, response);
	}

	private void handleForbiddenException(ForbiddenException ex, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ErrorResponse error = new ErrorResponse(new Date(), ex.getMessage(), request.getRequestURI());

		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.getWriter().write(error.toJson());
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		// Extrai o token da solicitação
		String header = request.getHeader("Authorization");
		System.out.println("PASSOU PELO getTokenFromRequest");

		if (header != null && header.startsWith("Bearer ")) {
			return header.substring(7); // Remove o prefixo "Bearer " e retorna o token restante
		}
		return null;
	}

}
