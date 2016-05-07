package br.com.elo7.spacecraft.commons.exception;

import br.com.elo7.spacecraft.commons.validation.ErrorExceptionCode;
import br.com.elo7.spacecraft.commons.validation.Errors;

public class CoordinateException extends RuntimeException {
	
	private static final long serialVersionUID = -7585457299766315168L;
	
	private final static ErrorExceptionCode errorExceptionCode = ErrorExceptionCode.COORDINATE_ERROR;
	
	private Errors errors;
	
	public CoordinateException() {
		super(errorExceptionCode.getMessage());
		errors = new Errors();
		errors.add(errorExceptionCode);
	}
	
	public CoordinateException(ErrorExceptionCode errorExceptionCode) {
		super(errorExceptionCode.getMessage());
		errors = new Errors();
		errors.add(errorExceptionCode);
	}
	
	public CoordinateException(ErrorExceptionCode errorExceptionCode, Throwable cause) {
		super(errorExceptionCode.getMessage(), cause);
		errors = new Errors();
		errors.add(errorExceptionCode);
	}
	
	public Errors getErrors() {
		return this.errors == null ? new Errors() : this.errors;
	}
	
}
