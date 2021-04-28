package com.junit5book.bookstoreadd;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Nested
@DisplayName("Search")
@ExtendWith(BooksParameterResolver.class)
public class BookShelfSearchSpec {
	private BookShelf shelf;
	private Book effectiveJava;
	private Book codeComplete;
	private Book mythicalManMath;
	private Book cleanCode;
	private Book refactoring;
	
	@BeforeEach
	void init(Map<String, Book> books) throws Exception
	{
		this.shelf = new BookShelf();
		this.effectiveJava = books.get("Effective Java");
		this.codeComplete = books.get("Code Complete");
		this.mythicalManMath = books.get("The Mythical Man-Month");
		this.cleanCode = books.get("Clean Code");
		this.refactoring = books.get("Refactoring: Improving the Design of Existing Code");
		this.shelf.add(effectiveJava, codeComplete, mythicalManMath, cleanCode, refactoring);
	}
	
	@Test
	@DisplayName(" should find books with title containing text")
	void shouldFindBooksWithTitleContainingText()
	{
		List<Book> books = shelf.findBooksByTitle("code");
		assertThat(books.size()).isEqualTo(3);
	}
	
	@Test
	@DisplayName(" should find books with title containing text and published after specified date")
	void shouldFilterSearchedBookBasedOnPublishedDate()
	{
		List<Book> books = shelf.findBooksByTitle("code", b -> b.getPublishedOn().isBefore(LocalDate.of(2014, 12, 31)));
		assertThat(books.size()).isEqualTo(3);
	}
}
