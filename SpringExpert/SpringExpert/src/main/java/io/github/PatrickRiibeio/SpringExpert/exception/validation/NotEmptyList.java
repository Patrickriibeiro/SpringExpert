package io.github.PatrickRiibeio.SpringExpert.exception.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import io.github.PatrickRiibeio.SpringExpert.exception.validation.constraintvalidation.NotEmptyListValidator;

@Retention(RetentionPolicy.RUNTIME) // anotação para determinar o tempo de execução da mesma.
@Target(ElementType.FIELD) // onde a anotações pode ser usada.
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {
	String message() default "A lista não pode ser vazia.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
