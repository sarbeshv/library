package com.project.LibraryManagement.common;

import java.util.List;

public class BadRequestException extends RuntimeException {

	private List<error> errors;

	public List<error> getErrors() {
		return errors;
	}

	public void setErrors(List<error> errors) {
		this.errors = errors;
	}

	public BadRequestException(String message,List<error> errors) {
		super(message);
		this.errors = errors;
	}
	

}
