package io.github.PatrickRiibeio.SpringExpert.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.github.PatrickRiibeio.SpringExpert.exception.validation.NotEmptyList;
import lombok.Data;

@Data
public class PedidoDTO {
	
	@NotNull(message = "Informe o código do cliente.")
	private Integer cliente;
	
	@NotNull(message = "Campo Total do pedido é obrigatório.")
	private BigDecimal total;
	
	@NotEmptyList(message = "Pedido não pode ser realizado sem itens")
	private List<ItensPedidoDTO> itensPerdido;
	

}
