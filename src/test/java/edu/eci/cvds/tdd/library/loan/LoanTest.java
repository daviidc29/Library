package edu.eci.cvds.tdd.library.loan;
 
import java.time.LocalDateTime;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
 
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
 
class LoanTest {
    @Test
    void testGetBook(){
        Book book=new Book("cien años de soledad", "Gabriel Garcia Marquez", "12345");
        Loan loan=new Loan();
        loan.setBook(book);
        assertEquals(book, loan.getBook());
    }
 
    @Test
    void testGetUser(){
        User user=new User();
        user.setName("Andres");
        user.setId("12345");
        Loan loan =new Loan();
        loan.setUser(user);
        assertEquals(user, loan.getUser());
 
 
    }
    @Test
    void testSetAndGetLoanDate() {
        Loan loan = new Loan();
        LocalDateTime loanDate = LocalDateTime.now();
       
        loan.setLoanDate(loanDate);
        assertEquals(loanDate, loan.getLoanDate());
    }
 
    @Test
    void testSetAndGetStatus() {
        Loan loan = new Loan();
        LoanStatus status = LoanStatus.ACTIVE;
       
        loan.setStatus(status);
        assertEquals(status, loan.getStatus());
    }
 
    @Test
    void testSetAndGetReturnDate() {
        Loan loan = new Loan();
        LocalDateTime returnDate = LocalDateTime.now().plusDays(7);
       
        loan.setReturnDate(returnDate);
        assertEquals(returnDate, loan.getReturnDate());
    }
 
}