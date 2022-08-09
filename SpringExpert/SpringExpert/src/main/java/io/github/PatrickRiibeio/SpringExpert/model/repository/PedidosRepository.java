package io.github.PatrickRiibeio.SpringExpert.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.PatrickRiibeio.SpringExpert.model.Entity.ClienteEntity;
import io.github.PatrickRiibeio.SpringExpert.model.Entity.PedidoEntity;

public interface PedidosRepository extends JpaRepository<PedidoEntity, Integer> {

	List<PedidoEntity> findByCliente(ClienteEntity cliente);
	
}
