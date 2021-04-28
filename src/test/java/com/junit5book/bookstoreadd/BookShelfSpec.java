package com.junit5book.bookstoreadd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Unit test for simple App.
 */
@ExtendWith(BooksParameterResolver.class)
public class BookShelfSpec 
{
    /**
     * Rigorous Test :-)
     */
	private BookShelf shelf;
	private Book effectiveJava;
	private Book codeComplete;
	private Book mythicalManMonth;
	private Book cleanCode;
	
	@BeforeEach
	void init(Map<String, Book> books) throws Exception {
		shelf = new BookShelf();
		this.effectiveJava = books.get("Effective Java");
		this.codeComplete = books.get("Code Complete");
		this.mythicalManMonth = books.get("The Mythical Man-Month");
		this.cleanCode = books.get("Clean Code");
	}
    @Test
    public void shelfEmptyWhenNoBookAdded() throws Exception {    	
    	List<Book> books = shelf.getBooks();
    	assertTrue(books.isEmpty(), () -> "Bookshelf should be empty");
    }
    
    @Test
    void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
    	shelf.add(effectiveJava, codeComplete);    	
    	List<Book> books = shelf.getBooks();
    	assertEquals(2, books.size(), () -> "Bookshelf should have two books");
    }
    
    @Test
    void emptyBookShelfWhenAddIsCalledWithoutBook()
    {
    	shelf.add();
    	List<Book> books = shelf.getBooks();
    	assertTrue(books.isEmpty(), () -> "Bookshelf should be empty");
    }
    
    @Test
    void booksReturnedFromBookShelfIsImmutableForClient()
    {
    	shelf.add(effectiveJava, codeComplete);    	
    	List<Book> books = shelf.getBooks();
    	try {
    		books.add(mythicalManMonth);
    		fail(() -> "Should not be able to add book to books");
    	}catch(Exception e) {
    		assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationExceptions");
    	}
    }
    
    @Test
    void bookshelfArrangedByBookTitle()
    {
    	shelf.add(effectiveJava, codeComplete, mythicalManMonth);
    	List<Book> books = shelf.arrange();    	
    	assertEquals(Arrays.asList(codeComplete, effectiveJava, mythicalManMonth), books, () -> "Books in a bookshelf should be arranged lexicographically by book title");    	    	
    }
    
    @Test
    void bookshelfArrangedByUserProvideCriteria()
    {
    	shelf.add(effectiveJava, codeComplete, mythicalManMonth);
    	List<Book> books = shelf.arrange(Comparator.<Book>naturalOrder().reversed());
    	assertEquals(Arrays.asList(mythicalManMonth, effectiveJava, codeComplete), books, () -> "Books in a bookshelf are arranged in descending order of book title");
    	
    }
}