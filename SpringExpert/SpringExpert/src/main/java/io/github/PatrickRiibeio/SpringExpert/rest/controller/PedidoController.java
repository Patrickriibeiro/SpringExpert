package io.github.PatrickRiibeio.SpringExpert.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.ItemPedido;
import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Pedido;
import io.github.PatrickRiibeio.SpringExpert.exception.PedidoNaoEncontradoException;
import io.github.PatrickRiibeio.SpringExpert.rest.dto.InformacaoItemPedidoDTO;
import io.github.PatrickRiibeio.SpringExpert.rest.dto.InformacoesPedidoDTO;
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

	@GetMapping(value = "{id}")
	public InformacoesPedidoDTO getById(@PathVariable("id") Integer id) {
		return service.obterPedidoCompleto(id).map(p -> converter(p))
				.orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado."));
	}

	private InformacoesPedidoDTO converter(Pedido pedido) {
		return InformacoesPedidoDTO.builder().codigo(pedido.getId())
				.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.cpf(pedido.getCliente().getCpf()).nomeCliente(pedido.getCliente().getNome()).total(pedido.getTotal())
				.status(pedido.getStatus().name()).itens(converter(pedido.getItens())).build();

	}

	private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
		if (CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		return itens.stream()
				.map(item -> InformacaoItemPedidoDTO.builder().descricaoProduto(item.getProduto().getDescricao())
						.precoUnitario(item.getProduto().getPreco()).quantidade(item.getQuantidade()).build())
				.collect(Collectors.toList());
	}
}
