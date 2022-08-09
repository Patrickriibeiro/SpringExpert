package io.github.PatrickRiibeio.SpringExpert.model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "item_pedido")
public class ItemPedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private PedidoEntity pedido;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private ProdutoEntity produto;

	@Column(name = "quantidade")
	private Integer quantidade;

}
