import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitTest {

    private Employee emp1;
    private Employee emp2;

    @BeforeEach
    public void setUp() {
        emp1 = new Employee("E001", "Alice", "Manager", 20.0);
        emp2 = new Employee("E002");
    }
    @Test
    public void testConstructorWithID() {
        assertEquals("E002", emp2.getId());
        assertNull(emp2.getName());
        assertNull(emp2.getPosition());
        assertNull(emp2.getPayment());
    }

    @Test
    public void testConstructorWithAllFields() {
        assertEquals("E001", emp1.getId());
        assertEquals("Alice", emp1.getName());
        assertEquals("Manager", emp1.getPosition());
        assertNotNull(emp1.getPayment());
        assertEquals(20.0, emp1.getPayment().getHourlyRate());
    }
    

    @Test
    void testGetName() {
        assertEquals("Alice", emp1.getName());
    }

    @Test
    void testGetPosition() {
        assertEquals("Manager", emp1.getPosition());
    }

    @Test
    void testLogHours() {
        emp1.logHours(5);
        assertEquals(5, emp1.getPayment().getHoursWorked());
        
        emp1.logHours(3);
        assertEquals(8, emp1.getPayment().getHoursWorked());
    }

    //Waiting for Josh to Finish
    @Test
    void testModifyRoom() {

    }
    //Waiting for Josh to Finish
    @Test
    void testModifyAmenity() {

    }

    @Test
    void testSetWage() {
        emp1.setWage(25.0);
        assertEquals(25.0, emp1.getPayment().getHourlyRate());
    }
    @Test
    void testDisplayEmployeeDetails() {
        emp1.displayEmployeeDetails();
        // Manual verification required for console output; consider refactoring to return a string instead
    }

    @Test
    void testEquals() {
        Employee emp3 = new Employee("E001", "Alice", "Employee", 25.0);
        Employee emp4 = new Employee("E003", "Bob", "Eaaaa", 23);
        Employee emp5 = new Employee("E003", "Bob", "Eaaaa", 23);


        assertTrue(emp1.equals(emp3)); // Same ID 
        assertFalse(emp1.equals(emp2)); // different IDs
        assertFalse(emp1.equals(emp4)); // Different IDs
        assertFalse(emp1.equals(null)); // Null comparison
        assertTrue(emp4.equals(emp5)); // Same everything
        assertFalse(emp1.equals("Blah blah blah")); // Different object type
    }
    
}
