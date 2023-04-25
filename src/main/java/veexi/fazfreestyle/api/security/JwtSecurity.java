package veexi.fazfreestyle.api.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import veexi.fazfreestyle.api.exceptions.handle.ForbiddenException;

@Component
public class JwtSecurity {

	@Value("${jwt_chave_secreta}")
	private String JWT_SECRET;

	@Value("${jwt_expiracao_milisegundos}")
	private String JWT_EXPIRATION_MS;

	public String gerarToken(String username) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + Long.parseLong(JWT_EXPIRATION_MS));

		return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}

	public boolean validarToken(String token) throws ForbiddenException {
		try {
			if (token == null) {
				throw new ForbiddenException("Token nulo");
			}
			System.out.println("isTokenValid -> " + token);

			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);

			return true;
		} catch (ExpiredJwtException ex) {
			throw new ForbiddenException("Token expirado");
		} catch (JwtException ex) {
			throw new ForbiddenException("Token inválido");
		} catch (NullPointerException ex) {
			throw new ForbiddenException("Token nulo");
		}

	}

	public Claims obterClaimsDoToken(String token) {
		return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
	}
}
