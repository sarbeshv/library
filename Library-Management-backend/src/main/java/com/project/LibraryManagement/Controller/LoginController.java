package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.DTO.LoginRequestDTO;
import com.project.LibraryManagement.DTO.SignUpRequestDTO;
import com.project.LibraryManagement.Service.LoginService;
import  com.project.LibraryManagement.common.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
	
	@Autowired
	LoginService loginService;

	@PostMapping("/Register")
	public ResponseEntity<APIResponse> signUp( @RequestBody SignUpRequestDTO signupDTo) {
		APIResponse response = loginService.signUp(signupDTo);
		System.out.println(signupDTo);
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	
	@PostMapping("/Login")
	public ResponseEntity<APIResponse> login( @RequestBody LoginRequestDTO loginDto) {
		APIResponse response = loginService.login(loginDto);
		
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}



}
