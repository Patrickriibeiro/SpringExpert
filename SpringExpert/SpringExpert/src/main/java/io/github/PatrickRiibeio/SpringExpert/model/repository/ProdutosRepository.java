package io.github.PatrickRiibeio.SpringExpert.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.PatrickRiibeio.SpringExpert.model.Entity.ProdutoEntity;

public interface ProdutosRepository extends JpaRepository<ProdutoEntity, Integer> {

	
}
