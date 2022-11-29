package io.github.PatrickRiibeio.SpringExpert.domain.Entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "produto")
public class Produto {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "id")
     private Integer id;
	 
	 @Column(name = "descricao")
     @NotEmpty(message = "Campos descricao é obrigatório")
     private String descricao;
	 
	 @Column(name = "preco_unitario")
	 @NotNull(message = "Campo preço é obrigatório.")
     private BigDecimal preco;
	
}

