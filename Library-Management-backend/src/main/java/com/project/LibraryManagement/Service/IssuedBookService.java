package com.project.LibraryManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LibraryManagement.Repository.IssuedBookRepository;
import com.project.LibraryManagement.common.Constant;
import com.project.LibraryManagement.entity.Books;
import com.project.LibraryManagement.entity.IssuedBook;



@Service
public class IssuedBookService {

	@Autowired
	private IssuedBookRepository issuedBookRepository;
	
	public List<IssuedBook> getAll() {
		return issuedBookRepository.findAll();
	}
	
	public IssuedBook get(Long id) {
		return issuedBookRepository.findById(id).get();
	}
	
	public Long getCountByBook(Books book) {
		return issuedBookRepository.countByBookAndReturned(book, Constant.BOOK_NOT_RETURNED);
	}
	
	public IssuedBook save(IssuedBook issuedBook) {
		return issuedBookRepository.save(issuedBook);
	}
	
	public IssuedBook addNew(IssuedBook issuedBook) {
		issuedBook.setReturned( Constant.BOOK_NOT_RETURNED );
		return issuedBookRepository.save(issuedBook);
	}
	public List<IssuedBook> findAllIssue(Long id){
		return issuedBookRepository.findAllByIssueId(id);
		
	}
	public void delete(Long id) {
	    issuedBookRepository.deleteById(id);
	}


}
