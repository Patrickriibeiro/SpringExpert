package io.github.PatrickRiibeio.SpringExpert.rest.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.PatrickRiibeio.SpringExpert.exception.ApiErrors;
import io.github.PatrickRiibeio.SpringExpert.exception.PedidoNaoEncontradoException;
import io.github.PatrickRiibeio.SpringExpert.exception.RegraDeNegocioException;
import io.github.PatrickRiibeio.SpringExpert.exception.SenhaInvalidaException;

//@ControllerAdvice
@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RegraDeNegocioException.class)
	@ResponseStatus(BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(RegraDeNegocioException ex) {
		String mensagemErro = ex.getMessage();
		return new ApiErrors(mensagemErro);
	}

	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(NOT_FOUND)
	public ApiErrors HandlePedidoNaoEncotrado(PedidoNaoEncontradoException ex) {
		return new ApiErrors(ex.getMessage());
	}

	@ExceptionHandler(SenhaInvalidaException.class)
	@ResponseStatus(BAD_REQUEST)
	public ApiErrors HandlePedidoNaoEncotrado(SenhaInvalidaException ex) {
		return new ApiErrors(ex.getMessage());
	}
	 
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 @ResponseStatus(BAD_REQUEST)
	 public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
		List<String> errors =  ex.getBindingResult().getAllErrors()
		 .stream()
		 .map(erro -> erro.getDefaultMessage())
		 .collect(Collectors.toList());
		 
		 return new ApiErrors(errors);
	}
	
}
