package io.github.PatrickRiibeio.SpringExpert.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Usuario;
import io.github.PatrickRiibeio.SpringExpert.domain.repository.UsuarioRepository;
import io.github.PatrickRiibeio.SpringExpert.exception.SenhaInvalidaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UserDetailsService {

	private final PasswordEncoder encoder;
	
	private final UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
		
	public UserDetails autenticar(Usuario usuario) throws SenhaInvalidaException {
		UserDetails user = this.loadUserByUsername(usuario.getLogin());
		log.info("Password UserDatails : " + user);
		boolean senhasBatem = encoder.matches(usuario.getSenha(),user.getPassword());
		if(senhasBatem) {
			
			return user;
		}
		throw new SenhaInvalidaException("Usúario ou senha incorretos");
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado na base de dados"));

		String[] roles = usuario.isAdmin() ? new String[] {"ADMIN","USER"} : new String[]{"USER"};
			
		return User
				.builder()
				.username(usuario.getLogin())
				.password(usuario.getSenha())
				.roles(roles)
				.build();
	}
	
}
