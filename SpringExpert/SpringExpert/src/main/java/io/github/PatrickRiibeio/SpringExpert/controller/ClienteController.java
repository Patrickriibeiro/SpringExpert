package io.github.PatrickRiibeio.SpringExpert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.PatrickRiibeio.SpringExpert.model.Entity.ClienteEntity;
import io.github.PatrickRiibeio.SpringExpert.model.repository.ClientesRepositorySpringDataJpa;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClientesRepositorySpringDataJpa clientesRep;

	@GetMapping("{id}")
	public ClienteEntity getClienteById(@PathVariable Integer id) {
		return clientesRep.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteEntity save(@RequestBody ClienteEntity cliente) {
		return clientesRep.save(cliente);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		clientesRep.findById(id).map(cliente -> {
			clientesRep.delete(cliente);
			return cliente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody ClienteEntity cliente) {
		clientesRep.findById(id).map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			clientesRep.save(cliente);
			return clienteExistente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@GetMapping
	public List<ClienteEntity> find(ClienteEntity filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Example<ClienteEntity> example = Example.of(filtro, matcher);
		return clientesRep.findAll(example);
	}

}
