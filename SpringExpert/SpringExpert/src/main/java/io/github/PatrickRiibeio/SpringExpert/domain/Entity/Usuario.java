package io.github.PatrickRiibeio.SpringExpert.domain.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "login")
	@NotEmpty(message = "{campo.usuario.obrigatorio}")
	private String login;

	@Column(name = "senha")
	@NotEmpty(message = "{campo.senha.obrigatorio}")
	private String senha;

	@Column(name = "admin")
	private boolean admin;

}
