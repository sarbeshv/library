package com.project.LibraryManagement.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.LibraryManagement.Repository.IssuedBookRepository;
import com.project.LibraryManagement.Service.BookService;
import com.project.LibraryManagement.Service.IssueService;
import com.project.LibraryManagement.Service.IssuedBookService;
import com.project.LibraryManagement.Service.MemberService;
import com.project.LibraryManagement.common.Constant;
import com.project.LibraryManagement.common.IssuePayload;
import com.project.LibraryManagement.common.ReturnBooksPayload;
import com.project.LibraryManagement.entity.Books;
import com.project.LibraryManagement.entity.Member;
import com.project.LibraryManagement.entity.Issue;
import com.project.LibraryManagement.entity.IssuedBook;


@RestController
@RequestMapping(value = "/rest/issue")
@CrossOrigin(origins = "http://localhost:3000")
public class IssuedBookController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private IssueService issueService;
	
	@Autowired
	private IssuedBookService issuedBookService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestBody IssuePayload payload) {

	    String memberIdStr = payload.getMember();
	    List<String> bookIdsStr = payload.getBooks();
	    Date expectedDate = payload.getExpectedDate();
	    String notes = payload.getNotes();

	    Long memberId = null;
	    List<Long> bookIds = new ArrayList<>();

	    try {
	        memberId = Long.parseLong(memberIdStr);
	        for (String bookIdStr : bookIdsStr) {
	            bookIds.add(Long.parseLong(bookIdStr));
	        }
	    } catch (NumberFormatException ex) {
	        ex.printStackTrace();
	        return "invalid number format";
	    }

	    Member member = memberService.get(memberId);
	    List<Books> books = bookService.get(bookIds);

	    Issue issue = new Issue();
	    issue.setMember(member);
	    issue.setExpectedReturnDate(expectedDate); // Set expected date
	    issue.setNotes(notes); // Set notes
	    issue = issueService.addNew(issue);

	    for (Books book : books) {
	        book.setStatusOfBook(Constant.BOOK_STATUS_ISSUED);
	        book = bookService.save(book);

	        IssuedBook ib = new IssuedBook();
	        ib.setBook(book);
	        ib.setIssue(issue);
	        issuedBookService.addNew(ib);
	    }

	    return "success";
	}

	
	@RequestMapping(value = "/{id}/return/all", method = RequestMethod.GET)
	public String returnAll(@PathVariable(name = "id") Long id) {
		Issue issue = issueService.get(id);
		if( issue != null ) {
			List<IssuedBook> issuedBooks = issue.getIssuedBooks();
			for( int k=0 ; k<issuedBooks.size() ; k++ ) {
				IssuedBook ib = issuedBooks.get(k);
				ib.setReturned( Constant.BOOK_RETURNED );
				issuedBookService.save( ib );
				
				Books book = ib.getBook();
				book.setStatusOfBook(Constant.BOOK_STATUS_AVAILABLE);
				bookService.save(book);
			}
			
			issue.setReturned( Constant.BOOK_RETURNED );
			issueService.save(issue);
			
			return "successful";
		} else {
			return "unsuccessful";
		}
	}
	
	@RequestMapping(value="/{id}/return", method = RequestMethod.POST)
	public String returnSelected(@RequestBody ReturnBooksPayload payload, @PathVariable(name = "id") Long id) {
		System.out.println("Payload: " + payload);
	    Issue issue = issueService.get(id);
	    List<Long> issuedBookIds = payload.getIds();
	    System.out.println("Ids: " + issuedBookIds);
	    if (issue != null && issuedBookIds != null) {
	    	String[] issuedBookIdArray = issuedBookIds.stream().map(String::valueOf).toArray(String[]::new);
	        List<IssuedBook> issuedBooks = issue.getIssuedBooks();
	        for (int k = 0; k < issuedBooks.size(); k++) {
	            IssuedBook ib = issuedBooks.get(k);
	            if (Arrays.asList(issuedBookIdArray).contains(ib.getId().toString())) {
	                ib.setReturned(Constant.BOOK_RETURNED);
	                issuedBookService.save(ib);
	                Books book = ib.getBook();
	                book.setStatusOfBook(Constant.BOOK_STATUS_AVAILABLE);
	                bookService.save(book);
	            }
	        }
	        return "successful";
	    } else {
	        return "unsuccessful";
	    }
	}

	
	@Autowired
	IssuedBookRepository issueRepo;
	
	
	@GetMapping("/find/{id}")
	public List<IssuedBook> findByIssuesId(@PathVariable Long id ){
		return issueRepo.findAllByIssueId(id);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(name = "id") Long id) {
	    try {
	        issuedBookService.delete(id);
	        return "success";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}

	
}
