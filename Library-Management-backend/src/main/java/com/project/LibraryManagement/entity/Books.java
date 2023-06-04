package com.project.LibraryManagement.entity;

 

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name ="Books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Books {
	
	
    @GeneratedValue
	@Id
	private Long bookId;
	
	private String bookName;
	private String Author;
	private Integer statusOfBook;
	

	@ManyToOne
	@JoinColumn(name = "category_id")
	@Nonnull
	private Category category;
	
	private String genre;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public Integer getStatusOfBook() {
		return statusOfBook;
	}

	public void setStatusOfBook(Integer statusOfBook) {
		this.statusOfBook = statusOfBook;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Books(Long bookId, String bookName, String author, Integer statusOfBook, Category category, String genre) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		Author = author;
		this.statusOfBook = statusOfBook;
		this.category = category;
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Books [bookId=" + bookId + ", bookName=" + bookName + ", Author=" + Author + ", statusOfBook="
				+ statusOfBook + ", category=" + category + ", genre=" + genre + "]";
	}

	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}


}