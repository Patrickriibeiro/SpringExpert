package io.github.PatrickRiibeio.SpringExpert.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Cliente;
import io.github.PatrickRiibeio.SpringExpert.domain.Entity.ItemPedido;
import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Pedido;
import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Produto;
import io.github.PatrickRiibeio.SpringExpert.domain.enums.StatusPedido;
import io.github.PatrickRiibeio.SpringExpert.domain.repository.ClientesRepositorySpringDataJpa;
import io.github.PatrickRiibeio.SpringExpert.domain.repository.ItemPedidosRepository;
import io.github.PatrickRiibeio.SpringExpert.domain.repository.PedidosRepository;
import io.github.PatrickRiibeio.SpringExpert.domain.repository.ProdutosRepository;
import io.github.PatrickRiibeio.SpringExpert.exception.RegraDeNegocioException;
import io.github.PatrickRiibeio.SpringExpert.rest.dto.ItensPedidoDTO;
import io.github.PatrickRiibeio.SpringExpert.rest.dto.PedidoDTO;
import io.github.PatrickRiibeio.SpringExpert.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

	private final PedidosRepository repository;
	private final ClientesRepositorySpringDataJpa clientesRepository;
	private final ProdutosRepository produtosRepository;
	private final ItemPedidosRepository itemPedidoRepository;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Cliente cliente = clientesRepository.findById(dto.getCliente())
				.orElseThrow(() -> new RegraDeNegocioException("Código de cliente inválido."));

		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.REALIZADO);

		List<ItemPedido> itemPedido = converterItems(pedido, dto.getItensPerdido());

		repository.save(pedido);
		itemPedidoRepository.saveAll(itemPedido);
		pedido.setItens(itemPedido);

		return pedido;
	}
	
	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		return repository.findByIdFetchItens(id);	
	}
	
	

	private List<ItemPedido> converterItems(Pedido pedido, List<ItensPedidoDTO> itensPedido) {
		if (itensPedido.isEmpty()) {
			throw new RegraDeNegocioException("Não é possível realizar um pedido sem itens");
		}

		return itensPedido.stream().map(dto -> {

			Produto produto = produtosRepository.findById(dto.getProduto())
					.orElseThrow(() -> new RegraDeNegocioException("Código de produto inválido"));

			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			return itemPedido;

		}).collect(Collectors.toList());

	}

}
