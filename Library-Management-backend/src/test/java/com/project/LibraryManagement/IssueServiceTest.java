package com.project.LibraryManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.LibraryManagement.Repository.IssueRepository;
import com.project.LibraryManagement.common.Constant;
import com.project.LibraryManagement.entity.Issue;
import com.project.LibraryManagement.entity.Member;
import com.project.LibraryManagement.Service.IssueService;

public class IssueServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private IssueService issueService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        List<Issue> expectedIssues = new ArrayList<>();
        expectedIssues.add(new Issue());
        expectedIssues.add(new Issue());

        when(issueRepository.findAll()).thenReturn(expectedIssues);

        List<Issue> actualIssues = issueService.getAll();

        assertEquals(expectedIssues.size(), actualIssues.size());
    }

    @Test
    public void testGet() {
        Long issueId = 234L;
        Issue expectedIssue = new Issue();
        expectedIssue.setId(issueId);

        when(issueRepository.findById(issueId)).thenReturn(Optional.of(expectedIssue));

        Issue actualIssue = issueService.get(issueId);

        assertNotNull(actualIssue);
        assertEquals(issueId, actualIssue.getId());
    }

    @Test
    public void testGetAllUnreturned() {
        List<Issue> expectedIssues = new ArrayList<>();
        expectedIssues.add(new Issue());
        expectedIssues.add(new Issue());

        when(issueRepository.findByReturned(Constant.BOOK_NOT_RETURNED)).thenReturn(expectedIssues);
        
        List<Issue> actualIssues = issueService.getAllUnreturned();
        assertEquals(expectedIssues.size(), actualIssues.size());
    }

    @Test
    public void testAddNew() {
        Issue issue = new Issue();

        when(issueRepository.save(issue)).thenReturn(issue);

        Issue savedIssue = issueService.addNew(issue);

        assertNotNull(savedIssue);
        assertNotNull(savedIssue.getIssueDate());
        assertEquals(Constant.BOOK_NOT_RETURNED, savedIssue.getReturned());
    }

    @Test
    public void testSave() {
        Issue issue = new Issue();
        issue.setId(1L);

        when(issueRepository.save(issue)).thenReturn(issue);

        Issue savedIssue = issueService.save(issue);

        assertNotNull(savedIssue);
        assertEquals(issue.getId(), savedIssue.getId());
    }

    @Test
    public void testGetCountByMember() {
        Member member = new Member();
        Long expectedCount = 6L;

        when(issueRepository.countByMemberAndReturned(member, Constant.BOOK_NOT_RETURNED)).thenReturn(expectedCount);

        Long actualCount = issueService.getCountByMember(member);

        assertEquals(expectedCount, actualCount);
    }
}

