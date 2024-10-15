import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class JUnitTest {

    private Employee emp1;
    private Employee emp2;
    private VIPRoom VIPRoom;
    private StandardRoom standardRoom;
    private Guest guest;
    private Guest guest2;
    private Hotel hotel;
    private Pool pool;
    

    @BeforeEach
    public void setUp() {
        emp1 = new Employee("E001", "Alice", "Manager", 20.0);
        emp2 = new Employee("E002");
        guest = new Guest("Alice Smith");
        guest2 = new Guest ("Bob Will");
        standardRoom = new StandardRoom(guest, 101, 100.0); 
        VIPRoom = new VIPRoom(guest, 201, 200.0); 
        pool = new Pool("Pool", "Swimming Pool", 30);
        hotel = new Hotel("Test Hotel");

        hotel.addRooms(standardRoom);
        hotel.addRooms(VIPRoom);
        hotel.addAmenities(pool);
        pool.reserve(guest);
        guest.setRoom(standardRoom);

        
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

    

    //This test will remove guest from the room without having another guest assigned to the room right after.
    @Test
    void testModifyRoomWithOnlyCheckingGuestOut() {
        emp1.modifyRoom(standardRoom);
        assertEquals(guest.getRoom(), null, "Guest should be checked out from room.");
        assertTrue(hotel.getOpenRooms().contains(standardRoom), "Room should be marked as open");
    }

    //This testwill remove the current guest and check in another guest right away.
    @Test
    void testModifyRoomWithCheckingOutAndCheckingIn() {
        emp1.modifyRoom(standardRoom, guest2, hotel);
        assertEquals(guest.getRoom(), null, "First guest should be checked out from room.");
        assertEquals(guest2.getRoom(), standardRoom, "Room should not be reserved to guest 2");
        assertFalse(hotel.getOpenRooms().contains(standardRoom), "Room should be marked as reserved");
    }
    //Check out guest from amenity without having another guest reserve
    @Test
    void testModifyAmenityWithOnlyCheckingOut() {
        assertTrue(guest.getAmenitiesBooked().contains(pool), "The guest should reserved pool");

        emp1.modifyAmenity(pool, guest);
        // Check if the guest has the amenity in their booking list
        assertFalse(guest.getAmenitiesBooked().contains(pool), "The guest should finished booking pool");

        // Check if the pool is marked as reserved
        assertTrue(pool.isAvailable, "The pool should be marked as available.");
    }

    //This test will remove the current guest and reserve for another guest
    @Test
    void testModifyAmenityWithCheckingOutAndCheckingIn() {        
        emp1.modifyAmenity(pool, guest, guest2, hotel);
        // Check if the guest has the amenity in their booking list
        assertFalse(guest.getAmenitiesBooked().contains(pool), "The guest should finished booking pool");
        assertTrue(guest2.getAmenitiesBooked().contains(pool), "The guest should finished booking pool");

        // Check if the pool is marked as reserved
        assertTrue(pool.isAvailable, "The pool should be marked as available.");
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
