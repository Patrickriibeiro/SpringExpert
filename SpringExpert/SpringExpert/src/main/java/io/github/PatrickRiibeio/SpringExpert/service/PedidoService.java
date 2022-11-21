package io.github.PatrickRiibeio.SpringExpert.service;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Pedido;
import io.github.PatrickRiibeio.SpringExpert.rest.dto.PedidoDTO;

public interface PedidoService {

   	Pedido salvar(PedidoDTO dto);

}
