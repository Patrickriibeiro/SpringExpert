package io.github.PatrickRiibeio.SpringExpert.domain.Entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
//@JsonInclude(value = Include.NON_NULL)
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome",length = 100)
	@NotEmpty(message = "Campo nome é obrigatório.")
	private String nome;
	
	@Column(name = "cpf", length = 11)
	@NotEmpty(message = "Campo CPF é obrigatório.")
	@CPF(message = "CPF inválido.")
	private String cpf;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")//, fetch = FetchType.LAZY, irá trazer o resultado da fk, não é uma boa pratica por pesar a pesquisa e qualquer pesquisa nessa entidade irá trazer os pedidos
	private Set<Pedido> pedidos;
	
	
	public Cliente(String nome) {
		this.nome = nome;
	}


	public Cliente(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	
	
}
