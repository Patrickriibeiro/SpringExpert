package io.github.PatrickRiibeio.SpringExpert.exception;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ApiErrors {

	private List<String> errors;

	public ApiErrors(String mensagemErro) {
		this.errors = Arrays.asList(mensagemErro);
	}

	public ApiErrors(List<String> errors) {
		super();
		this.errors = errors;
	}

}
