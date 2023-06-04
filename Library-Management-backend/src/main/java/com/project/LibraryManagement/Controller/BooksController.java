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

import com.project.LibraryManagement.Exception.BookNotFoundException;
import com.project.LibraryManagement.Repository.BookRepository;
import com.project.LibraryManagement.Service.BookService;
import com.project.LibraryManagement.common.APIResponse;
import com.project.LibraryManagement.common.Constant;
import com.project.LibraryManagement.entity.Books;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/books")
public class BooksController {

	@Autowired
	BookService bookService;
	
	@Autowired
	BookRepository bookrepo;

	@GetMapping
	public APIResponse getAllBooks() {
		return bookService.getAllBooks();
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> addBook(@RequestBody Books book) {
		 boolean bookExists = bookService.checkIfBookExists(book.getBookName());
		    if (bookExists) {
		        return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate book found.");
		    }
		book.setStatusOfBook(Constant.BOOK_STATUS_AVAILABLE);
		bookService.save(book);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setData("Added successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}

	@GetMapping(value = "/{id}")
	public APIResponse getBookById(@PathVariable("id") Long id) {
		APIResponse response = null;
		try {
			response = bookService.viewBookById(id);
		} catch (BookNotFoundException e) {
			// Handle the exception or log the error message
			e.printStackTrace();
			response = new APIResponse();
			response.setError("Book not found");
		}
		return response;
	}

//	@GetMapping(value = "/genre/{input}")
//	public APIResponse getBooksByGenre(@PathVariable("input") String input) {
//		return bookService.getBookByGenre(input);
//	}

	
	@PutMapping("/update/{id}")
	public Books updateBook(@RequestBody Books newBook, @PathVariable Long id) throws BookNotFoundException {
		return bookrepo.findById(id)
				.map(book ->{
					book.setBookName(newBook.getBookName());
					book.setCategory(newBook.getCategory());
					book.setAuthor(newBook.getAuthor());
					return bookService.save(book);
				}).orElseThrow(()-> new BookNotFoundException());
	}


	@DeleteMapping("/delete/{id}")
	public APIResponse deleteBook(@PathVariable Long id) throws BookNotFoundException {
		APIResponse response = new APIResponse();
		if(!bookrepo.existsById(id)) {
			throw new BookNotFoundException();
		}
	     bookrepo.deleteById(id);
	     String result ="Book with id "+id+ " has been successfully";
	     response.setData(result);
	     return response;
	}
}
