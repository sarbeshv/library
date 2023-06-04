package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.data.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LibraryManagement.Exception.BookNotFoundException;
import com.project.LibraryManagement.Repository.BookRepository;
import com.project.LibraryManagement.common.APIResponse;
import com.project.LibraryManagement.entity.Books;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	BookData bookData = new BookData();
	APIResponse response = new APIResponse();
	
	
	public APIResponse getAllBooks(){
		
		 List<Books> book = bookRepository.findAll();
		 bookData.setBooks(book);
		 response.setData(bookData);
		return response;
	}
	
	
	public boolean checkIfBookExists(String bookName) {
	    return bookRepository.existsByBookName(bookName);
	}

	
	public APIResponse viewBookById(Long id) throws BookNotFoundException  {
	
		BookData viewData = new BookData();
		Books book = bookRepository.findById(id).orElse(null);
		viewData.setBook(book);
		response.setData(viewData);
		if(book == null)
			throw new BookNotFoundException("Book id Not exist");
		return response;
	}
	
	
	
	public List<Books> get(List<Long> ids) {
		return bookRepository.findAllById(ids);
	}
	
	public Books save(Books book) {
		return bookRepository.save(book);
	}
	

	
}
