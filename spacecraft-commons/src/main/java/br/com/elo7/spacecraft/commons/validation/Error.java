package br.com.elo7.spacecraft.commons.validation;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class Error implements Serializable {
	
	private static final long serialVersionUID = 5926150667902957868L;
	
	
	private int code;
	
	private String message;
	
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
	
}
