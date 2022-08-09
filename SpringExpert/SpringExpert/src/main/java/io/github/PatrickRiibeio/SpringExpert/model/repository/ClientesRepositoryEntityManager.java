package io.github.PatrickRiibeio.SpringExpert.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.PatrickRiibeio.SpringExpert.model.Entity.ClienteEntity;

@Repository
public class ClientesRepositoryEntityManager {

	@Autowired
	private EntityManager entityManager; // classe responsável em gerar ciclo de vidas nas Entitys;
	
	@Transactional
	public ClienteEntity salvar(ClienteEntity cliente) {
		entityManager.persist(cliente); // antes de salvar(entidade transiente, não possui id) pós salvar vira manager.
		return cliente;
	}
	
	@Transactional
	public ClienteEntity atualizar(ClienteEntity cliente) {
		entityManager.merge(cliente);// vai da update na entidade manager.
		return cliente;
	}

	public void deletar(Integer id) {
		ClienteEntity cliente = entityManager.find(ClienteEntity.class, id);
		deletar(cliente);
	}

	@Transactional
	public void deletar(ClienteEntity cliente) {
		if(!entityManager.contains(cliente))
			cliente = entityManager.merge(cliente);
			
		entityManager.remove(cliente);
	}

	@Transactional(readOnly = true)
	public List<ClienteEntity> buscarPorNome(String nome) {
		String jpql = "Select c from Cliente c where c.nome like :nome";
		TypedQuery<ClienteEntity> query = entityManager.createQuery(jpql, ClienteEntity.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}

	public List<ClienteEntity> obterTodos() {
		return entityManager.createQuery("from Cliente", ClienteEntity.class).getResultList();
	}

}
