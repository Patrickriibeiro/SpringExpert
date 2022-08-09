package io.github.PatrickRiibeio.SpringExpert.model.Entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "produto")
public class ProdutoEntity {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "id")
     private Integer id;
	 
	 @Column(name = "descricao")
     private String descricao;
	 
	 @Column(name = "preco_unitario")
     private BigDecimal preco;
	
}

