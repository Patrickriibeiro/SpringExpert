package io.github.PatrickRiibeio.SpringExpert.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Usuario;
import io.github.PatrickRiibeio.SpringExpert.exception.SenhaInvalidaException;
import io.github.PatrickRiibeio.SpringExpert.rest.dto.CredenciaisDTO;
import io.github.PatrickRiibeio.SpringExpert.rest.dto.TokenDTO;
import io.github.PatrickRiibeio.SpringExpert.securityjwt.JwtService;
import io.github.PatrickRiibeio.SpringExpert.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioServiceImpl usuarioService;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	@PostMapping
	@ResponseStatus(CREATED)
	public Usuario salvar(@RequestBody @Valid Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return usuarioService.salvar(usuario);
	}
	
	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
		try {
			Usuario usuario = Usuario.builder()
					.login(credenciais.getLogin())
					.senha(credenciais.getSenha())
					.build();
			
			usuarioService.autenticar(usuario);
			String token = jwtService.gerarToken(usuario);
			
			return new TokenDTO(usuario.getLogin(), token);
			
		} catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

}
