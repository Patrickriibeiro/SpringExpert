package io.github.PatrickRiibeio.SpringExpert.domain.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.github.PatrickRiibeio.SpringExpert.domain.enums.StatusPedido;
import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(name = "data_pedido")
	private LocalDate dataPedido;
	
	@Column(name = "total", precision = 20, scale = 2)// scale casas decimais.
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING) //EnumType valor do enum como string.
	@Column(name = "status")
	private StatusPedido status;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;
	
}
