package com.project.LibraryManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.LibraryManagement.Repository.IssuedBookRepository;
import com.project.LibraryManagement.Service.IssuedBookService;
import com.project.LibraryManagement.common.Constant;
import com.project.LibraryManagement.entity.Books;
import com.project.LibraryManagement.entity.IssuedBook;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IssuedBookServiceTest {

    @Mock
    private IssuedBookRepository issuedBookRepository;

    @InjectMocks
    private IssuedBookService issuedBookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        
    	List<IssuedBook> expectedList = Arrays.asList(
                new IssuedBook(),
                new IssuedBook()
        );
        when(issuedBookRepository.findAll()).thenReturn(expectedList);

        List<IssuedBook> actualList = issuedBookService.getAll();

       
        assertEquals(expectedList.size(), actualList.size());
    }

    @Test
    void testGet() {
       
        Long id = 1566L;
        IssuedBook expectedIssuedBook = new IssuedBook(/* initialize with desired values */);
        when(issuedBookRepository.findById(id)).thenReturn(Optional.of(expectedIssuedBook));

        IssuedBook actualIssuedBook = issuedBookService.get(id);

        assertEquals(expectedIssuedBook, actualIssuedBook);
    }

    @Test
    void testGetCountByBook() {
        Books book = new Books();
        Long expectedCount = 2L;
        when(issuedBookRepository.countByBookAndReturned(book, Constant.BOOK_NOT_RETURNED)).thenReturn(expectedCount);

        Long actualCount = issuedBookService.getCountByBook(book);

        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testSave() {
        IssuedBook issuedBook = new IssuedBook(/* initialize with desired values */);
        when(issuedBookRepository.save(issuedBook)).thenReturn(issuedBook);

        IssuedBook savedIssuedBook = issuedBookService.save(issuedBook);

        assertEquals(issuedBook, savedIssuedBook);
    }

    @Test
    void testAddNew() {
        IssuedBook issuedBook = new IssuedBook(/* initialize with desired values */);
        when(issuedBookRepository.save(issuedBook)).thenReturn(issuedBook);

        IssuedBook addedIssuedBook = issuedBookService.addNew(issuedBook);

        assertEquals(issuedBook, addedIssuedBook);
    }

    @Test
    void testFindAllIssue() {
        Long id = 565L;
        List<IssuedBook> expectedList = Arrays.asList(
                new IssuedBook(/* initialize with desired values */),
                new IssuedBook(/* initialize with desired values */)
        );
        when(issuedBookRepository.findAllByIssueId(id)).thenReturn(expectedList);

        List<IssuedBook> actualList = issuedBookService.findAllIssue(id);

        assertEquals(expectedList.size(), actualList.size());
    }
}
