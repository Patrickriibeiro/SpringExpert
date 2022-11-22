package io.github.PatrickRiibeio.SpringExpert.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.PatrickRiibeio.SpringExpert.exception.RegraDeNegocioException;
import io.github.PatrickRiibeio.SpringExpert.utils.ApiErrors;

//@ControllerAdvice
@RestControllerAdvice
public class ApplicationControllerAdvice {

	 @ExceptionHandler(RegraDeNegocioException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
     public ApiErrors handleRegraNegocioException(RegraDeNegocioException ex) {
    	 String mensagemErro = ex.getMessage();
    	 return new ApiErrors(mensagemErro);
     }
	
}
