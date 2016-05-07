package br.com.elo7.spacecraft.commons.validation;

public enum ErrorExceptionCode {
	
	CRASH_ERROR(1, "Has one body in this coordinates. Please try again."),
	COORDINATE_ERROR(2, "Don't have more space in plateau. Please try again."),
	BEAN_VALIDATOR_ERROR(3, "Bean validator error.");
	
	private int code;
	
	private String message;
	
	private ErrorExceptionCode(int code, String message) {
		this.code = code;
		this.message =message;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
}
