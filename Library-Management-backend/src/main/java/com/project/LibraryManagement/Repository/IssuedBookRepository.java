package com.project.LibraryManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.LibraryManagement.entity.Books;
import com.project.LibraryManagement.entity.IssuedBook;


@Repository
public interface IssuedBookRepository extends JpaRepository<IssuedBook, Long> {

	Long countByBookAndReturned(Books book, Integer bookNotReturned);

	List<IssuedBook> findAllByIssueId(Long id);
}