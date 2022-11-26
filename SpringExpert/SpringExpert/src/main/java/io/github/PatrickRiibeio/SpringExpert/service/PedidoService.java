package io.github.PatrickRiibeio.SpringExpert.service;

import java.util.Optional;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Pedido;
import io.github.PatrickRiibeio.SpringExpert.domain.enums.StatusPedido;
import io.github.PatrickRiibeio.SpringExpert.rest.dto.PedidoDTO;

public interface PedidoService {

   	Pedido salvar(PedidoDTO dto);
   	
   	Optional<Pedido> obterPedidoCompleto(Integer id);
   	
   	void atualizarStatusPedido(Integer id, StatusPedido status);

}
