package io.github.PatrickRiibeio.SpringExpert.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Usuario;
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
       return Jwts
    		      .builder()
    		      .setSubject(usuario.getLogin())
    		      .setExpiration(data)
    		      .signWith(SignatureAlgorithm.HS512, chaveDeAssinatura)
    		      .compact();
	}
}
