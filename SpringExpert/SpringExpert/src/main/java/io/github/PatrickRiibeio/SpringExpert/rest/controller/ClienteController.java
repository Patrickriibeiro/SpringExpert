package io.github.PatrickRiibeio.SpringExpert.rest.controller;

import java.util.List;

import javax.validation.Valid;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/clientes")
//@ApiIgnore
@Api("Api Clientes")
public class ClienteController {

	private ClientesRepositorySpringDataJpa repository;
	
	public ClienteController(ClientesRepositorySpringDataJpa repository) {
		this.repository = repository;
	}

	@GetMapping("{id}")
	@ApiOperation("Obter detalhes do um cliente")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cliente encontrado"),
		@ApiResponse(code = 400, message = "Cliente não encotrado para o ID informado.")
	})
	public Cliente getClienteById(@PathVariable @ApiParam("Id do Cliente")  Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Salva um novo cliente")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
		@ApiResponse(code = 400, message = "Cliente não encotrado para o ID informado.")
	})
	public Cliente save(@RequestBody @Valid Cliente cliente) {
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
	public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
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
