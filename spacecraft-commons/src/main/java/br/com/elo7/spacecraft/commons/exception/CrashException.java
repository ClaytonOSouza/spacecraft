package br.com.elo7.spacecraft.commons.exception;

import br.com.elo7.spacecraft.commons.validation.ErrorExceptionCode;
import br.com.elo7.spacecraft.commons.validation.Errors;

public class CrashException extends RuntimeException {
	
	private static final long serialVersionUID = -1137771432203016143L;
	
	private Errors errors;
	
	private final static ErrorExceptionCode errorExceptionCode = ErrorExceptionCode.CRASH_ERROR;
	
	public CrashException() {
		super(errorExceptionCode.getMessage());
		errors = new Errors();
		errors.add(errorExceptionCode);
	}
	
	public CrashException(ErrorExceptionCode errorExceptionCode) {
		super(errorExceptionCode.getMessage());
		errors = new Errors();
		errors.add(errorExceptionCode);
	}
	
	public CrashException(ErrorExceptionCode errorExceptionCode, Throwable cause) {
		super(errorExceptionCode.getMessage(), cause);
		errors = new Errors();
		errors.add(errorExceptionCode);
	}
	
	public Errors getErrors() {
		return this.errors == null ? new Errors() : this.errors;
	}
	
}
