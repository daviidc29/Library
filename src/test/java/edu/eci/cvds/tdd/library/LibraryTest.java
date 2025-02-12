package edu.eci.cvds.tdd.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

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
        assertNotNull(loan, "The loan should be created successfully.");
        assertEquals(LoanStatus.ACTIVE, loan.getStatus(), "The loan status should be ACTIVE.");
        assertEquals(user, loan.getUser(), "The loan user should match the provided user.");
        assertEquals(book, loan.getBook(), "The loan book should match the provided book.");

    } 

    @Test
    void testIsNotLoanABook(){
        assertFalse(library.addBook(null), "Adding a null book should return false.");
        library.addUser(user);
        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        assertNull(loan, "The loan should not be created if the book is not available.");
        library.addBook(book);
        loan = library.loanABook("nonexistent-user", book.getIsbn());
        assertNull(loan, "The loan should not be created if the user does not exist.");
        
    } 

    @Test
    void testIsReturnLoan(){
        library.addUser(user);
        library.addBook(book);
        Loan loan = library.loanABook(user.getId(), book.getIsbn());

        library.returnLoan(loan);
        Loan result = library.returnLoan(loan);

        assertNull(result, "The loan should be null if it was already returned.");

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
}
