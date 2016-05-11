package br.com.elo7.spacecraft.commons.exception;

import br.com.elo7.spacecraft.commons.validation.ErrorExceptionCode;
import br.com.elo7.spacecraft.commons.validation.Errors;

public class InternalServerErrorException extends RuntimeException {
	
	private static final long serialVersionUID = 2858529788244731988L;

	private Errors errors;
	
	private final static ErrorExceptionCode errorExceptionCode = ErrorExceptionCode.INTERNAL_SERVER_ERROR;
	
	public InternalServerErrorException() {
		super(errorExceptionCode.getMessage());
		errors = new Errors();
		errors.add(errorExceptionCode);
	}
	
	public InternalServerErrorException(ErrorExceptionCode errorExceptionCode) {
		super(errorExceptionCode.getMessage());
		errors = new Errors();
		errors.add(errorExceptionCode);
	}
	
	public InternalServerErrorException(ErrorExceptionCode errorExceptionCode, Throwable cause) {
		super(errorExceptionCode.getMessage(), cause);
		errors = new Errors();
		errors.add(errorExceptionCode);
	}
	
	public Errors getErrors() {
		return this.errors == null ? new Errors() : this.errors;
	}

}
