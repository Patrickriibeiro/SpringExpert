package io.github.PatrickRiibeio.SpringExpert.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.PatrickRiibeio.SpringExpert.model.Entity.ClienteEntity;

public interface ClientesRepositorySpringDataJpa extends JpaRepository<ClienteEntity, Integer> {

	List<ClienteEntity> findByNomeLike(String nome); // Convenção, transforma em query.
    
	List<ClienteEntity> findByNomeOrIdOrderById(String nome, Integer id);

	ClienteEntity findOneByNome(String nome);

	boolean existsByNome(String nome);
	
	@Modifying
	void deleteByNome(String nome);

	@Query(value = " select c from Cliente c where c.nome like %:nome% ")
	List<ClienteEntity> encontrarPorNome(@Param("nome") String nome);

	@Query("select c from Cliente c left join fetch c.pedidos where c.id = :id")
    ClienteEntity findClienteFetchPedidos(@Param("id") Integer id);

}
