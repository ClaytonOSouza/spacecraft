package br.com.elo7.spacecraft.commons.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class Errors implements Serializable {
	
	private static final long serialVersionUID = 2068588416282590389L;
	
	
	private List<Error> errors;
	
	public Errors() {
		this.errors = new ArrayList<>();
	}
	
	public List<Error> getErrors() {
		return this.errors;
	}
	
	public int size() {
		return this.errors.size();
	}
	
	public void add(ErrorExceptionCode errorExceptionCode) {
		Error error = new Error(errorExceptionCode);
		errors.add(error);
	}
	
}
