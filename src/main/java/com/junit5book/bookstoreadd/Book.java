package com.junit5book.bookstoreadd;

import java.time.LocalDate;

public class Book implements Comparable<Book>{
	private final String title;
	private final String author;
	private final LocalDate publishedOn;
	private LocalDate startedReadingOn;
	private LocalDate finishedReadingOn;
	public Book(String title, String author, LocalDate publishedOn) {
		super();
		this.title = title;
		this.author = author;
		this.publishedOn = publishedOn;
	}
	
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public LocalDate getPublishedOn() {
		return publishedOn;
	}
	
	public void startedReadingOn(LocalDate startedOn)
	{
		this.startedReadingOn = startedOn;
	}
	
	public void finishedReadingOn(LocalDate finishedOn)
	{
		this.finishedReadingOn = finishedOn;
	}
	
	public boolean isRead()
	{
		return startedReadingOn != null && finishedReadingOn != null;
	}
		
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publishedOn=" + publishedOn + "]";
	}

	@Override
	public int compareTo(Book o) {
		// TODO Auto-generated method stub
		return this.title.compareTo(o.title);
	}

}
