package com.brasil.prev.challenge.dto.model.response;

public class DTOError {
	
	private String field;
	private String error;
	
	public DTOError(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getFiel() {
		return field;
	}

	public String getErro() {
		return error;
	}

}
