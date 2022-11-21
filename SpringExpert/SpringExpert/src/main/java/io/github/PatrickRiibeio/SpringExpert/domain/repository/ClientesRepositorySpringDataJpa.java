package io.github.PatrickRiibeio.SpringExpert.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Cliente;

public interface ClientesRepositorySpringDataJpa extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNomeLike(String nome); // Convenção, transforma em query.
    
	List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

	Cliente findOneByNome(String nome);

	boolean existsByNome(String nome);
	
	@Modifying
	void deleteByNome(String nome);

	@Query(value = " select c from Cliente c where c.nome like %:nome% ")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);

	@Query("select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

}
