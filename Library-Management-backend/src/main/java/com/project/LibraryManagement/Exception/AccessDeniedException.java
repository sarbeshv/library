package com.project.LibraryManagement.Exception;

public class AccessDeniedException extends RuntimeException{

	public AccessDeniedException(String message){
		super(message);
	}
}
