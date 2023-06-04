package com.project.LibraryManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LibraryManagement.Repository.MemberRepository;
import com.project.LibraryManagement.entity.Member;

	@Service
	public class MemberService {

		@Autowired
		private MemberRepository memberRepository;
		
		
		public Long getTotalCount() {
			return memberRepository.count();
		}
		
		public List<Member> getAllMembers() {
			return memberRepository.findAll();
		}
		
		public Member getMemberById(Long id) {
			return memberRepository.findById(id).get();
		}
		
		public Member addNewMember(Member member) {
			return memberRepository.save( member );
		}
		
		public Member save(Member member) {
			return memberRepository.save( member );
		}
		
		public void delete(Long id) {
			memberRepository.deleteById(id);
		}
		public Member get(Long id) {
			return memberRepository.findById(id).get();
		}
		public boolean checkIfMemberExistsByEmail(String email) {
		    return memberRepository.existsByEmail(email);
		}

		public boolean checkIfMemberExistsByPhoneNumber(String phoneNumber) {
		    return memberRepository.existsByPhoneNumber(phoneNumber);
		}

		

}
