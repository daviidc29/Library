package edu.eci.cvds.tdd.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

class LibraryTest {
    private Library library;
    private Map<Book, Integer> books;
    private Book book;
    private User user;

    @BeforeEach
    public void setUp(){
        library = new Library();
        books = new HashMap<>();
        book = new Book("Cien años de soledad", "Gabriel Marquez", "9788497592208");        
        user = new User();
        user.setName("John");
        user.setId("12345");
    }
    
    @Test
    void testIsAddBook(){
        books.put(book, books.getOrDefault(book, 0) + 1);
        assertTrue(library.addBook(book));
        assertTrue(library.getBooks().containsKey(book));
        assertEquals(1, library.getBooks().get(book));
    } 

    @Test
    void testIsNotAddBook(){
        Book book2 = null;
        library.addBook(book2);
        assertFalse(library.getBooks().containsKey(null));
        assertEquals(null, library.getBooks().get(book2));
    } 

    @Test
    void testIsLoanABook(){
        library.addUser(user);
        library.addBook(book);
        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull(loan);
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
        assertEquals(user, loan.getUser());
        assertEquals(book, loan.getBook());

    } 

    @Test
    void testIsNotLoanABook(){
        assertFalse(library.addBook(null));
        library.addUser(user);
        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        assertNull(loan);
        library.addBook(book);
        loan = library.loanABook("-1", book.getIsbn());
        assertNull(loan);
        library.addUser(user);
        library.addBook(book);
        library.loanABook(user.getId(), book.getIsbn());
        Loan secondLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNull(secondLoan);

    } 

    @Test
    void testIsReturnLoan(){
        library.addUser(user);
        library.addBook(book);
        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        
        assertNotNull(loan);
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
        
        Loan returnedLoan = library.returnLoan(loan);
        
        assertNotNull(returnedLoan);
        assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus());
        assertNotNull(returnedLoan.getReturnDate());
        assertEquals(1, library.getBooks().get(book));
    }
    
    @Test
    void testIsNotReturnLoan(){
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setStatus(LoanStatus.RETURNED);
        
        Loan returnedLoan = library.returnLoan(loan);
        assertNull(returnedLoan);
        
        Loan nullLoan = library.returnLoan(null);
        assertNull(nullLoan);
    } 

    @Test
    void testIsAddUser(){
        
    }

    @Test
    void testIsNotAddUser(){
        
    }

    @AfterEach
    public void afterTest(){

    }
}
