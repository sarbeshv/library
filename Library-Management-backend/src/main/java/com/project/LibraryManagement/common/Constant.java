package com.project.LibraryManagement.common;

public interface Constant {

	public interface USER_TYPE{
		String NORMAL = "NORMAL";
		String ADMIN = "ADMIN";
		
	}
	public static  Integer BOOK_STATUS_AVAILABLE = 1;
	public static final Integer BOOK_STATUS_ISSUED = 2;
	
	public static final Integer BOOK_NOT_RETURNED = 0;
	public static final Integer BOOK_RETURNED = 1;
}

