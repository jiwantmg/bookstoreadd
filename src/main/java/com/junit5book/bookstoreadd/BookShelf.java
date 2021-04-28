package com.junit5book.bookstoreadd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class BookShelf 
{	
	private final List<Book> books = new ArrayList<Book>();
	
	public List<Book> getBooks() {
		return Collections.unmodifiableList(this.books);
	}   
	
	public void add(Book... booksToAdd) {
		Arrays.stream(booksToAdd).forEach(books::add);
	}
	
	public List<Book> arrange() {
		return arrange(Comparator.naturalOrder());
	}
	
	public List<Book> arrange(Comparator<Book> criteria) {
		return books.stream().sorted(criteria).collect(Collectors.toList());
	}
	
	public Progress progress()
	{					
		int booksRead = Long.valueOf(this.books.stream().filter(Book::isRead).count()).intValue();		
		int booksToRead = books.size() - booksRead;
		int percentageCompleted = booksRead * 100 / books.size();
		int percentageToRead = booksToRead * 100 / books.size();
		return new Progress(percentageCompleted, percentageToRead, 0);
	}
	
	public List<Book> findBooksByTitle(String title)
	{
		return findBooksByTitle(title, b -> true);
	}
	
	public List<Book> findBooksByTitle(String title, BookFilter filter)
	{
		return books.stream().filter(b -> b.getTitle().toLowerCase().contains(title))
				.filter(b -> filter.apply(b))
				.collect(Collectors.toList());
	}
}
