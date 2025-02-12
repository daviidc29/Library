package edu.eci.cvds.tdd.library.book;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
 
class BookTest {
 
   @Test
    void testGetTittle(){
        Book book=new Book("cien años de soledad", "Gabriel Garcia Marquez", "12345");
        assertEquals("cien años de soledad", book.getTittle());
 
    }
 
    @Test
    void testGetAuthor(){
        Book book= new Book("cien años de soledad", "Gabriel Garcia Marquez", "12345");
        assertEquals("Gabriel Garcia Marquez", book.getAuthor());
 
    }
    @Test
    void testGetIsbn(){
        Book book = new Book("cien años de soledad","Gabriel Garcia Marquez","12345");
        assertEquals("12345", book.getIsbn());

    }
    
}

