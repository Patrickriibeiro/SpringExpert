package io.github.PatrickRiibeio.SpringExpert.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {

	
}
