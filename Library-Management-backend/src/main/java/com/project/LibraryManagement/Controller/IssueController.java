package com.project.LibraryManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.LibraryManagement.Service.CategoryService;
import com.project.LibraryManagement.Service.IssueService;
import com.project.LibraryManagement.entity.Category;
import com.project.LibraryManagement.entity.Issue;

@RestController
@RequestMapping(value = "/issues")
@CrossOrigin(origins = "http://localhost:3000")
public class IssueController {

	@Autowired
	private IssueService issueService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/unreturned")
	public List<Issue> getAllUnreturnedIssues() {
		return issueService.getAllUnreturned();
	}
	
	@GetMapping
	public List<Issue> getAllIssues() {
	    List<Issue> issues = issueService.getAll();
	    return issues;
	}

	
	@GetMapping("/categories")
	public List<Category> getAllCategories() {
		return categoryService.getAllBySort();
	}
	
	@PostMapping
	public Issue createIssue(@RequestBody Issue issue) {
		// Perform necessary validation and save logic
		return issueService.addNew(issue);
	}
}