package io.github.PatrickRiibeio.SpringExpert.securityjwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.PatrickRiibeio.SpringExpert.service.impl.UsuarioServiceImpl;

public class JwtAuthFilter extends OncePerRequestFilter {

	private JwtService jwtService;
	private UsuarioServiceImpl usuarioServiceImpl;

	public JwtAuthFilter(JwtService jwtService, UsuarioServiceImpl usuarioServiceImpl) {
		this.jwtService = jwtService;
		this.usuarioServiceImpl = usuarioServiceImpl;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {

		String authorization = httpServletRequest.getHeader("Authorization");

		if (authorization != null && authorization.startsWith("Bearer")) {
			String token = authorization.split(" ")[1];//transforma a string em dividir pelo regex.
			boolean isValid = jwtService.tokenValid(token);

			if (isValid) {
				String loginUsuario = jwtService.obterLoginUsuario(token);
				UserDetails usuario = usuarioServiceImpl.loadUserByUsername(loginUsuario);
				UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuario, null,
						usuario.getAuthorities());

				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(user);
			}
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}
