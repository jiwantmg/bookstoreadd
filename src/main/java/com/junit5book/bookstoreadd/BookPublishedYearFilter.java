package com.junit5book.bookstoreadd;

import java.time.LocalDate;

public class BookPublishedYearFilter implements BookFilter{
	private LocalDate startDate;
	
	static BookPublishedYearFilter After(int year) {
		BookPublishedYearFilter filter = new BookPublishedYearFilter();
		filter.startDate = LocalDate.of(year, 12, 31);
		return filter;
	}
	
	@Override
	public boolean apply(Book b) {
		// TODO Auto-generated method stub
		return b.getPublishedOn().isAfter(startDate);
	}

}
