package com.project.LibraryManagement.common;

import java.util.Date;
import java.util.List;

public class IssuePayload {

	 private String member;
	 private List<String> books;
	 private Date expectedDate;
	 private String notes;
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public List<String> getBooks() {
		return books;
	}
	public void setBooks(List<String> books) {
		this.books = books;
	}
	public Date getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	 
	 
	 

	    
}
