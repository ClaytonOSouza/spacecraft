package br.com.elo7.spacecraft.commons.validation;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class Error implements Serializable {
	
	private static final long serialVersionUID = 5926150667902957868L;
	
	
	private int code;
	
	private String message;
	
	private String invalidValue;
	
	private String param;
	
	public Error(ErrorExceptionCode errorExceptionCode, String invalidValue, String param, String message) {
		this.code = errorExceptionCode.getCode();
		this.invalidValue = invalidValue;
		this.param = param;
		this.message = message;
	}
	
	public Error(ErrorExceptionCode errorExceptionCode) {
		this.code = errorExceptionCode.getCode();
		this.message = errorExceptionCode.getMessage();
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getInvalidValue() {
		return invalidValue;
	}
	
	public String getParam() {
		return param;
	}
	
}
