package com.project.LibraryManagement.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LibraryManagement.Repository.IssueRepository;
import com.project.LibraryManagement.common.Constant;
import com.project.LibraryManagement.entity.Issue;
import com.project.LibraryManagement.entity.Member;



@Service
public class IssueService {

	@Autowired
	private IssueRepository issueRepository;
	
	public List<Issue> getAll() {
		return issueRepository.findAll();
	}
	
	public Issue get(Long id) {
		return issueRepository.findById(id).get();
	}
	
	public List<Issue> getAllUnreturned() {
		return issueRepository.findByReturned( Constant.BOOK_NOT_RETURNED );
	}
	
	public Issue addNew(Issue issue) {
		issue.setIssueDate( new Date() );
		issue.setReturned( Constant.BOOK_NOT_RETURNED );
		return issueRepository.save(issue);
	}
	
	public Issue save(Issue issue) {
		return issueRepository.save(issue);
	}
	
	public Long getCountByMember(Member member) {
		return issueRepository.countByMemberAndReturned(member, Constant.BOOK_NOT_RETURNED);
	}
}
