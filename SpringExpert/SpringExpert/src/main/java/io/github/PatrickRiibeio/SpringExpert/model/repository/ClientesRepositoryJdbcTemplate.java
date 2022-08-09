package io.github.PatrickRiibeio.SpringExpert.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.github.PatrickRiibeio.SpringExpert.model.Entity.ClienteEntity;

@Repository
public class ClientesRepositoryJdbcTemplate {

	private static String INSERT = " insert into cliente (nome) values (?) ";
	private static String SELECT_ALL = "Select * from cliente";
	private static String UPDATE = "update cliente set nome = ? where id = ?";
    public static String DELETE = "delete  from cliente where id = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;// Melhor opção para bancos legados ou mal estruturados, pq opera direto nas tabelas gerando mais flexibilidade.

	public ClienteEntity salvar(ClienteEntity cliente) {
		jdbcTemplate.update(INSERT, new Object[] { cliente.getNome() });
		return cliente;
	}

	public ClienteEntity atualizar(ClienteEntity cliente) {
		jdbcTemplate.update(UPDATE, new Object[] { cliente.getNome()});
		return cliente;
	};
	
	public void deletar(ClienteEntity cliente) {
	  	deletar(cliente.getId());
	}
	
	public void deletar(Integer id) {
		jdbcTemplate.update(DELETE, new Object[] {id});
	}
	 
	@SuppressWarnings("deprecation")
	public List<ClienteEntity> buscaPorNome(String nome ) {
		return jdbcTemplate.query(SELECT_ALL.concat(" where nome like ?"), new Object[]{"%" + nome + "%"},obterClienteMapper());
	}

	public List<ClienteEntity> listarClientes() {
		return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
	}

	private RowMapper<ClienteEntity> obterClienteMapper() {
		return new RowMapper<ClienteEntity>() {

			@Override
			public ClienteEntity mapRow(ResultSet resultSet, int i) throws SQLException {
				Integer id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				return new ClienteEntity(id, nome);
			}
		};
	}
}
