package com.project.LibraryManagement.common;

import java.util.List;

public class ReturnBooksPayload {
	  private List<Long> ids;

	  public List<Long> getIds() {
	    return ids;
	  }

	  public void setIds(List<Long> ids) {
	    this.ids = ids;
	  }
	}
