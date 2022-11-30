package io.github.PatrickRiibeio.SpringExpert.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.github.PatrickRiibeio.SpringExpert.exception.validation.NotEmptyList;
import lombok.Data;

@Data
public class PedidoDTO {
	
	@NotNull(message = "{campo.codigo-cliente.obrigatorio}")
	private Integer cliente;
	
	@NotNull(message = "{campo.total-pedido.obrigatorio}")
	private BigDecimal total;
	
	@NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
	private List<ItensPedidoDTO> itensPerdido;
	

}
