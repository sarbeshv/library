package com.project.LibraryManagement.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.LibraryManagement.Exception.MemberNotFoundException;
import com.project.LibraryManagement.Repository.MemberRepository;
import com.project.LibraryManagement.Service.MemberService;
import com.project.LibraryManagement.common.APIResponse;
import com.project.LibraryManagement.entity.Member;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepo;
	

@PostMapping(value="/add")
public ResponseEntity<?> addMember(@RequestBody Member member) {
    // Check if member with the same email or phone number already exists
    boolean isEmailExists = memberService.checkIfMemberExistsByEmail(member.getEmail());
    boolean isPhoneNumberExists = memberService.checkIfMemberExistsByPhoneNumber(member.getPhoneNumber());
    
    if (isEmailExists || isPhoneNumberExists) {
        // Member with same email or phone number already exists, return conflict status code
        APIResponse apiResponse = new APIResponse();
        apiResponse.setError("Member already exists");
        apiResponse.setStatus(409);
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate book found.");
    }
    
    // Member does not exist, add the member
    Member addedMember = memberService.addNewMember(member);
    
    // Return success status code and added member
    APIResponse apiResponse = new APIResponse();
    apiResponse.setData(addedMember);
    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
}


@GetMapping(value = "/{id}")
public Member getMemberById(@PathVariable("id") Long id) {
	Member response;
		response = memberRepo.findById(id).orElse(null);
	return response;
}

@DeleteMapping("/delete/{id}")
public APIResponse deleteMember(@PathVariable Long id) throws MemberNotFoundException {
	APIResponse response = new APIResponse();
	if(!memberRepo.existsById(id)) {
		throw new MemberNotFoundException();
	}
     memberRepo.deleteById(id);
     String result ="Member	with id "+id+ " has been successfully";
     response.setData(result);
     return response;
}

@GetMapping
public APIResponse GetAllMember(){
	APIResponse api =new APIResponse();
	api.setData(memberService.getAllMembers());
	return api;
}

@PutMapping("/update/{id}")
public Member updateBook(@RequestBody Member newMember, @PathVariable long id) throws MemberNotFoundException {
	return memberRepo.findById(id)
			.map(member->{
				member.setUserName(newMember.getUserName());
				member.setGender(newMember.getGender());
				member.setEmail(newMember.getEmail());
				member.setPhoneNumber(newMember.getPhoneNumber());
				return memberRepo.save(member);
			}).orElseThrow(()-> new MemberNotFoundException());
}

}
