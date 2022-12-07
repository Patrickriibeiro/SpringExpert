package io.github.PatrickRiibeio.SpringExpert.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	
	Optional<Usuario> findByLogin(String login);
}
