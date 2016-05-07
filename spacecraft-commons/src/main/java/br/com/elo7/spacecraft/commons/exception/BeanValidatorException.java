package br.com.elo7.spacecraft.commons.exception;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;

import br.com.elo7.spacecraft.commons.validation.ErrorExceptionCode;
import br.com.elo7.spacecraft.commons.validation.Errors;

public class BeanValidatorException extends RuntimeException {
	
	private static final long serialVersionUID = -5500101833467199694L;
	
	private Errors errors;
	
	private final static ErrorExceptionCode errorExceptionCode = ErrorExceptionCode.BEAN_VALIDATOR_ERROR;
	
	public BeanValidatorException() {
		super(errorExceptionCode.getMessage());
		errors = new Errors();
		errors.add(errorExceptionCode);
	}
	
	public BeanValidatorException(ErrorExceptionCode errorExceptionCode) {
		super(errorExceptionCode.getMessage());
		errors = new Errors();
		errors.add(errorExceptionCode);
	}
	
	public BeanValidatorException(ErrorExceptionCode errorExceptionCode, Throwable cause) {
		super(errorExceptionCode.getMessage(), cause);
		errors = new Errors();
		errors.add(errorExceptionCode);
	}
	
	public BeanValidatorException(Set<ConstraintViolation<Object>> errors) {
		this.errors = new Errors();
		Iterator<ConstraintViolation<Object>> iterator = errors.iterator();
		while(iterator.hasNext()){
			ConstraintViolation<Object> error = iterator.next();
			this.errors.add(errorExceptionCode, error.getInvalidValue(), error.getPropertyPath().toString(), error.getMessage());
		}
	}
	
	public Errors getErrors() {
		return this.errors == null ? new Errors() : this.errors;
	}

}
