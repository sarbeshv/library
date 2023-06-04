package com.project.LibraryManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.LibraryManagement.entity.Issue;
import com.project.LibraryManagement.entity.Member;



@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
	public List<Issue> findByReturned(Integer returned);
	public Long countByMemberAndReturned(Member member, Integer returned);
}
