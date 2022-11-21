package io.github.PatrickRiibeio.SpringExpert.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Cliente;
import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Pedido;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

	List<Pedido> findByCliente(Cliente cliente);
	
}
