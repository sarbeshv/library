package com.project.LibraryManagement.data;

import java.util.List;

import com.project.LibraryManagement.entity.Books;

public class BookData {

  private List<Books> books;
  
  private Books Book;

public Books getBook() {
	return Book;
}

public void setBook(Books book) {
	Book = book;
}

public List<Books> getBooks() {
	return books;
}

public void setBooks(List<Books> books) {
	this.books = books;
}
  

}
