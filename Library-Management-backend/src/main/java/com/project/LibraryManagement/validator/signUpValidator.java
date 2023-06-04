package com.project.LibraryManagement.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project.LibraryManagement.DTO.SignUpRequestDTO;
import com.project.LibraryManagement.common.error;
@Component
public class signUpValidator {
	
      

	public List<error> validSignUp(SignUpRequestDTO signUpRequest) {
		
		List<error> errors = new ArrayList<>();  
		
		if(signUpRequest.getUserName()== null) {
		   error er = new error("name","user name is null");
		   errors.add(er);
		}
		
		 String ALPHA_PATTERN = "^[A-Za-z\\s.]+$";
		 
		if(signUpRequest.getUserName().matches(ALPHA_PATTERN)==false) {
			errors.add(new error("name","user name is not valid"));
		}
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
		
		if(signUpRequest.getEmail()== null) {
			errors.add( new error("email","email is null"));
			   
			}
		
		if(signUpRequest.getEmail().matches(emailRegex)==false){
			errors.add( new error("email","email is invalid"));
		}
		
		if(signUpRequest.getPassword()== null) {
			errors.add( new error("password","password is null"));
			   
			}

		if(signUpRequest.getPassword().length() < 7){
			errors.add( new error("password","length must be atleast 7"));
		}


		return errors;
	
		
	}



}
