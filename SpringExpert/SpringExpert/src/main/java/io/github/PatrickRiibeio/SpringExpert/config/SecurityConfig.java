package io.github.PatrickRiibeio.SpringExpert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Encode spring, nunca sera encodado da mesma maneira.(Best).
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.passwordEncoder(passwordEncoder())
		.withUser("Patrick")
		.password(passwordEncoder().encode("8123"))
		.roles("USER","ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("api/clientes/**")
		   .hasAnyRole("USER","ADMIN")
		.antMatchers("/api/pedidos/**")
		   .hasAnyRole("USER","ADMIN")
		.antMatchers("/api/produtor/**")
		   .hasRole("ADMIN")		
		.and()
		   .httpBasic();
	}

}
