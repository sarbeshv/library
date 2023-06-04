package com.project.LibraryManagement.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.LibraryManagement.Exception.AccessDeniedException;

@ControllerAdvice
public class GobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity handleAllExpection(Exception e) {
		
		APIResponse response = new APIResponse();
		response.setError("Something went wrong...");
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
	}
	
	@ExceptionHandler
	public ResponseEntity handleBadRequestException(BadRequestException e) {
		
		APIResponse response = new APIResponse();
		response.setError(e.getErrors());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(response);
		
		
	}
	@ExceptionHandler
	public ResponseEntity handleAccessDeniedException(AccessDeniedException e) {
		
		APIResponse response = new APIResponse();
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setError(" UnAuthorized ");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(response);
		
		
	}


}
