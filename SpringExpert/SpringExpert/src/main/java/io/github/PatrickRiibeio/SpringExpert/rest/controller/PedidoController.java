package io.github.PatrickRiibeio.SpringExpert.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Pedido;
import io.github.PatrickRiibeio.SpringExpert.rest.dto.PedidoDTO;
import io.github.PatrickRiibeio.SpringExpert.service.PedidoService;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

	private PedidoService service;

	public PedidoController(PedidoService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Integer save(@RequestBody PedidoDTO dto) {
		Pedido pedido = service.salvar(dto);
		return pedido.getId();
	}
}
