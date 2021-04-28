package com.junit5book.bookstoreadd;

import java.util.ArrayList;
import java.util.List;

public class CompositeFilter implements BookFilter{
	private List<BookFilter> filters;
	
	public CompositeFilter() {
		filters = new ArrayList<BookFilter>();
	}
	
	@Override
	public boolean apply(Book b) {
		// TODO Auto-generated method stub
		return filters.stream()
				.map(bookFilter -> bookFilter.apply(b))
				.reduce(true, (b1, b2) -> b1 && b2);
	}
	
	void addFilter(final BookFilter bookFilter) {
		filters.add(bookFilter);
	}

}
