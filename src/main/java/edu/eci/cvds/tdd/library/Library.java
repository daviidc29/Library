package edu.eci.cvds.tdd.library;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book) {
        if (book != null) {
            books.put(book, books.getOrDefault(book, 0) + 1);
            return true;
        }
        return false;
    }

    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {
        User user = users.stream()
            .filter(u -> u.getId().equals(userId))
            .findFirst()
            .orElse(null);
        
        Book book = books.keySet().stream()
            .filter(b -> b.getIsbn().equals(isbn))
            .findFirst()
            .orElse(null);

        if (user == null || book == null || books.get(book) <= 0) {
            return null;
        }
        
        boolean alreadyHasLoan = loans.stream()
            .anyMatch(l -> l.getUser().getId().equals(userId) && 
                            l.getBook().getIsbn().equals(isbn) &&
                            l.getStatus() == LoanStatus.ACTIVE);
                                                
        if (alreadyHasLoan) {
            return null;
        }

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDateTime.now());
        loan.setStatus(LoanStatus.ACTIVE);
        loans.add(loan);
        books.put(book, books.get(book) - 1);
        return loan;
    }

    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */
    public Loan returnLoan(Loan loan) {
        if (loan == null || loan.getStatus() == LoanStatus.RETURNED) {
            return null;
        }
        
        // Buscar el préstamo en la lista para evitar problemas con equals
        Loan existingLoan = null;
        for (Loan l : loans) {
            if (l.equals(loan)) {
                existingLoan = l;
                break;
            }
        }
        
        if (existingLoan == null) {
            return null;
        }
        
        existingLoan.setStatus(LoanStatus.RETURNED);
        existingLoan.setReturnDate(LocalDateTime.now());
        
        Book book = existingLoan.getBook();
        books.put(book, books.getOrDefault(book, 0) + 1);
        
        return existingLoan;
    }
    
    
    
    public boolean addUser(User user) {
        return users.add(user);
    }

    public Map<Book, Integer> getBooks() {
        return books;
    }
    
}