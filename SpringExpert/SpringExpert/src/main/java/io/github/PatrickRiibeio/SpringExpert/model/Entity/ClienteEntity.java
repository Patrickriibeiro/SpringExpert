package io.github.PatrickRiibeio.SpringExpert.model.Entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome",length = 100)
	private String nome;
	
	@OneToMany(mappedBy = "cliente_id")//, fetch = FetchType.LAZY, irá trazer o resultado da fk, não é uma boa pratica por pesa a pesquisa e qualquer pesquisa nessa entidade irá trazer os pedidos
	private Set<PedidoEntity> pedidos;
	
	
	public ClienteEntity(String nome) {
		this.nome = nome;
	}


	public ClienteEntity(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	
	
}
