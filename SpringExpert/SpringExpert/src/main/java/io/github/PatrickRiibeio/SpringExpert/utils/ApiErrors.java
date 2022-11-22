package io.github.PatrickRiibeio.SpringExpert.utils;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class ApiErrors {
	
	private List<String> errors;
	
	public ApiErrors(String mensagemErro) {
		this.errors = Arrays.asList(mensagemErro);
	}

}
