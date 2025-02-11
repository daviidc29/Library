package edu.eci.cvds.tdd.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.user.User;

public class LibraryTest {
    private Library library;
    private Map<Book, Integer> books;
    private Book book;
    private List<User> users;
    private List<Loan> loans;


    @BeforeEach
    public void setUp(){
        library = new Library();
        books = new HashMap<>();
        book = new Book("Cien años de soledad", "Gabriel Marquez", "9788497592208");        
        users = new ArrayList<>();
        loans = new ArrayList<>();
    }
    
    @Test
    public void testIsAddBook(){
        books.put(book, books.getOrDefault(book, 0) + 1);
        assertTrue(library.addBook(book));
        assertTrue(library.getBooks().containsKey(book));
        assertEquals(1, library.getBooks().get(book));
    } 

    @Test
    public void testIsNotAddBook(){
        Book book = null;
        library.addBook(book);
        assertFalse(library.getBooks().containsKey(null));
        assertEquals(null, library.getBooks().get(book));
    } 

    @Test
    public void testIsLoanABook(){
        
    } 

    @Test
    public void testIsNotLoanABook(){
        
    } 

    @Test
    public void testIsReturnLoan(){
        
    }
    
    @Test
    public void testIsNotReturnLoan(){
        
    } 

    @Test
    public void testIsAddUser(){
        
    }

    @Test
    public void testIsNotAddUser(){
        
    }

    @AfterEach
    public void afterTest(){

    }
}
