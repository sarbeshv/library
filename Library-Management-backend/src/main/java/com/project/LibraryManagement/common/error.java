package com.project.LibraryManagement.common;

public class error {

	private String target;
	private String message;
	
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public error(String target, String message) {
		super();
		this.target = target;
		this.message = message;
	}
	
	

}
