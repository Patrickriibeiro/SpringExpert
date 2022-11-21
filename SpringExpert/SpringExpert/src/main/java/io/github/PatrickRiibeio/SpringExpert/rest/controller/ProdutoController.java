package io.github.PatrickRiibeio.SpringExpert.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

import io.github.PatrickRiibeio.SpringExpert.domain.Entity.Produto;
import io.github.PatrickRiibeio.SpringExpert.domain.repository.ProdutosRepository;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutosRepository prodrep;

    public ProdutoController(ProdutosRepository prodrep) {
		this.prodrep = prodrep;
	}

	@PostMapping
    @ResponseStatus(CREATED)
    public Produto save( @RequestBody Produto produto ){
        return prodrep.save(produto);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody Produto produto ){
        prodrep
                .findById(id)
                .map( p -> {
                   produto.setId(p.getId());
                   prodrep.save(produto);
                   return produto;
                }).orElseThrow( () ->
                new ResponseStatusException(NOT_FOUND,
                        "Produto não encontrado."));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        prodrep
                .findById(id)
                .map( p -> {
                    prodrep.delete(p);
                    return Void.TYPE;
                }).orElseThrow( () ->
                new ResponseStatusException(NOT_FOUND,
                        "Produto não encontrado."));
    }

    @GetMapping("{id}")
    public Produto getById(@PathVariable Integer id){
        return prodrep
                .findById(id)
                .orElseThrow( () ->
                new ResponseStatusException(NOT_FOUND,
                        "Produto não encontrado."));
    }

    @GetMapping
    public List<Produto> find(Produto filtro ){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example<Produto> example = Example.of(filtro, matcher);
        return prodrep.findAll(example);
    }
}