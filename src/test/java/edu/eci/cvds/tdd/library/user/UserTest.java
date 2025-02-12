package edu.eci.cvds.tdd.library.user;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
 
class UserTest {
 
    @Test
    void testGetname(){
        User user =new User();
        user.setName("Andres");
        assertEquals("Andres", user.getName());
 
    }
 
    @Test
    void testGetId(){
        User user =new User();
        user.setId("123456");
        assertEquals("123456", user.getId());
    }
 
    @Test
    void testSetname(){
        User user =new User();
        user.setName("Ivan");
        user.setName("Antonio");
        assertEquals("Antonio", user.getName());
 
    }
    @Test
    void testSetId(){
        User user =new User();
        user.setId("123456");
        user.setId("9876543");
        assertEquals("9876543", user.getId());
    }
}