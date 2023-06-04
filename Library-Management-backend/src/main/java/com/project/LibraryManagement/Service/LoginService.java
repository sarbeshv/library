package com.project.LibraryManagement.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.LibraryManagement.DTO.LoginRequestDTO;
import com.project.LibraryManagement.DTO.SignUpRequestDTO;
import com.project.LibraryManagement.Repository.UsersRepository;
import com.project.LibraryManagement.common.APIResponse;
import com.project.LibraryManagement.common.BadRequestException;
import com.project.LibraryManagement.common.error;
import com.project.LibraryManagement.entity.Users;
import com.project.LibraryManagement.util.JwtUtils;
import com.project.LibraryManagement.validator.signUpValidator;

@Service
public class LoginService {
	
	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private signUpValidator signValidator;
	
	@Autowired
	private JwtUtils jwtUtils;

	public APIResponse signUp(SignUpRequestDTO signUpRequest) {
		
		
	//validation	
	List<error> errors = signValidator.validSignUp(signUpRequest);
		if(errors.size()>0) {
			throw new BadRequestException("bad Request",errors);
		}
		
		
		APIResponse response = new APIResponse();
		Users user1 = new Users();
		
		//dto to entity
		user1.setUserName(signUpRequest.getUserName());
		user1.setEmail(signUpRequest.getEmail());
		user1.setPassword(signUpRequest.getPassword());
		
		// store
		user1 = userRepo.save(user1);
		
//		Map<String,Object> data = new HashMap<>();
//		  String token = jwtUtils.generateJwt(user1);
//		  data.put("accessToken", token);
  	      response.setData(user1);
		
		return response;
	}

	public APIResponse login(LoginRequestDTO loginDto) {
		
		APIResponse response = new APIResponse();
		Users user= userRepo.findOneByEmailIgnoreCaseAndPassword(loginDto.getEmail(),loginDto.getPassword()); 
		
		if(user == null) {
			response.setData("User login failed");
			response.setStatus(403);
			return response;
		}
		
//		Map<String,Object> data = new HashMap<>();
//		  String token = jwtUtils.generateJwt(user);
//		  data.put("accessToken", token);
    	  response.setData(user);	
		
			return response;

		

	}
}
