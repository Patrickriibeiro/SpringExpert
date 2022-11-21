package io.github.PatrickRiibeio.SpringExpert.rest.controller;

import java.util.List;

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

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Cliente;
import io.github.PatrickRiibeio.SpringExpert.domain.repository.ClientesRepositorySpringDataJpa;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	private ClientesRepositorySpringDataJpa repository;

	public ClienteController(ClientesRepositorySpringDataJpa repository) {
		this.repository = repository;
	}

	@GetMapping("{id}")
	public Cliente getClienteById(@PathVariable Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save(@RequestBody Cliente cliente) {
		return repository.save(cliente);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		repository.findById(id).map(cliente -> {
			repository.delete(cliente);
			return cliente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Cliente cliente) {
		repository.findById(id).map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			repository.save(cliente);
			return clienteExistente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@GetMapping
	public List<Cliente> find(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Example<Cliente> example = Example.of(filtro, matcher);
		return repository.findAll(example);
	}

}
