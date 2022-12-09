package io.github.PatrickRiibeio.SpringExpert.securityjwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtService {

	@Value("${security.jwt.expiracao}")
	private String expiracao;

	@Value("${secutiry.jwt.chave-assinatura}")
	private String chaveDeAssinatura;

	public String gerarToken(Usuario usuario) {
       long expString = Long.valueOf(expiracao);
       LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString); //Somar Hora atual + valor da expiracao
       Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant()); 
       
       HashMap<String,Object> claims = new HashMap<>(); //Informações do token.
       claims.put("emaildoUsuario", "usuario@gmail.com");
       claims.put("roles","admin");
      
       return Jwts
    		      .builder()
    		      .setSubject(usuario.getLogin())
    		      .setExpiration(data)
    		      .setClaims(claims)
    		      .signWith(SignatureAlgorithm.HS512, chaveDeAssinatura)
    		      .compact();
	}

	public boolean tokenValid(String token) {
		try {
			Claims claims = obterClaims(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime data = dataExpiracao.toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDateTime();

			return !LocalDateTime.now().isAfter(data);

		} catch (Exception e) {
			return false;
		}
	}
	
	public String obterLoginUsuario(String token) throws ExpiredJwtException {
		return (String) obterClaims(token).getSubject();
	}
	
	private Claims obterClaims(String token) throws ExpiredJwtException {
		return Jwts
				   .parser()
				   .setSigningKey(chaveDeAssinatura)
				   .parseClaimsJws(token)
				   .getBody();
	}
	
	
}
