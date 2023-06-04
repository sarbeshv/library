package com.project.LibraryManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.project.LibraryManagement.entity.Books;


@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

	boolean existsByBookName(String bookName);

	}
