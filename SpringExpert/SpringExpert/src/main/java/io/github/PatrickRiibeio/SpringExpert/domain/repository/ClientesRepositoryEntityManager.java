package io.github.PatrickRiibeio.SpringExpert.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Cliente;

@Repository
public class ClientesRepositoryEntityManager {

	@Autowired
	private EntityManager entityManager; // classe responsável em gerar ciclo de vidas nas Entitys;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		entityManager.persist(cliente); // antes de salvar(entidade transiente, não possui id) pós salvar vira manager.
		return cliente;
	}
	
	@Transactional
	public Cliente atualizar(Cliente cliente) {
		entityManager.merge(cliente);// vai da update na entidade manager.
		return cliente;
	}

	public void deletar(Integer id) {
		Cliente cliente = entityManager.find(Cliente.class, id);
		deletar(cliente);
	}

	@Transactional
	public void deletar(Cliente cliente) {
		if(!entityManager.contains(cliente))
			cliente = entityManager.merge(cliente);
			
		entityManager.remove(cliente);
	}

	@Transactional(readOnly = true)
	public List<Cliente> buscarPorNome(String nome) {
		String jpql = "Select c from Cliente c where c.nome like :nome";
		TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}

	public List<Cliente> obterTodos() {
		return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
	}

}
