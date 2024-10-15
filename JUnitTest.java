import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.stream;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;



public class JUnitTest {

    //FOR EMPLOYEE CLASS TESTING
    private Employee emp1;
    private Employee emp2;
    private VIPRoom VIPRoom;
    private StandardRoom standardRoom;
    private Guest guest;
    private Guest guest2;
    private Hotel hotel;
    private Pool pool;

    //FOR GUEST CLASS TESTIN
    private Guest guest3;
    private StandardRoom standardRoom2;
    private VIPRoom VIPRoom2;
    private Gym gym;
    private GuestPayment guestPayment;

    

    @BeforeEach
    public void setUp() {
        //FOR EMPLOYEE CLASS TESTING    
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

        //FOR GUEST CLASS TESTING
        standardRoom2 = new StandardRoom(101); 
        VIPRoom2 = new VIPRoom(201); 
        gym = new Gym("Gym", "Workout", 100);
        guest3 = new Guest("Jane Trey");
        guestPayment = new GuestPayment();

        
    }
    //-----------FOR EMPLOYEE CLASS TESTING--------------
    @Test
    public void testEmployeeConstructorWithID() {
        assertEquals("E002", emp2.getId());
        assertNull(emp2.getName());
        assertNull(emp2.getPosition());
        assertNull(emp2.getPayment());
    }

    @Test
    public void testEmployeeConstructorWithAllFields() {
        assertEquals("E001", emp1.getId());
        assertEquals("Alice", emp1.getName());
        assertEquals("Manager", emp1.getPosition());
        assertNotNull(emp1.getPayment());
        assertEquals(20.0, emp1.getPayment().getHourlyRate());
    }
    

    @Test
    void testEmployeeGetName() {
        assertEquals("Alice", emp1.getName());
    }

    @Test
    void testEmployeeGetPosition() {
        assertEquals("Manager", emp1.getPosition());
    }

    @Test
    void testEmployeeLogHours() {
        emp1.logHours(5);
        assertEquals(5, emp1.getPayment().getHoursWorked());
        
        emp1.logHours(3);
        assertEquals(8, emp1.getPayment().getHoursWorked());
    }

    

    //This test will remove guest from the room without having another guest assigned to the room right after.
    @Test
    void testEmployeeModifyRoomWithOnlyCheckingGuestOut() {
        emp1.modifyRoom(standardRoom);
        assertEquals(guest.getRoom(), null, "Guest should be checked out from room.");
        assertTrue(hotel.getOpenRooms().contains(standardRoom), "Room should be marked as open");
    }

    //This testwill remove the current guest and check in another guest right away.
    @Test
    void testEmployeeModifyRoomWithCheckingOutAndCheckingIn() {
        emp1.modifyRoom(standardRoom, guest2, hotel);
        assertEquals(guest.getRoom(), null, "First guest should be checked out from room.");
        assertEquals(guest2.getRoom(), standardRoom, "Room should not be reserved to guest 2");
        assertFalse(hotel.getOpenRooms().contains(standardRoom), "Room should be marked as reserved");
    }
    //Check out guest from amenity without having another guest reserve
    @Test
    void testEmployeeModifyAmenityWithOnlyCheckingOut() {
        assertTrue(guest.getAmenitiesBooked().contains(pool), "The guest should reserved pool");

        emp1.modifyAmenity(pool, guest);
        // Check if the guest has the amenity in their booking list
        assertFalse(guest.getAmenitiesBooked().contains(pool), "The guest should finished booking pool");

        // Check if the pool is marked as reserved
        assertTrue(pool.isAvailable, "The pool should be marked as available.");
    }

    //This test will remove the current guest and reserve for another guest
    @Test
    void testEmployeeModifyAmenityWithCheckingOutAndCheckingIn() {        
        emp1.modifyAmenity(pool, guest, guest2, hotel);
        // Check if the guest has the amenity in their booking list
        assertFalse(guest.getAmenitiesBooked().contains(pool), "The pool should not be in current list of amenity booked for guest 1");
        assertTrue(guest2.getAmenitiesBooked().contains(pool), "The guest 2 should finished booking pool");

        // Check if the pool is marked as reserved
        assertTrue(pool.isAvailable, "The pool should be marked as available.");
    }


    @Test
    void testEmployeeSetWage() {
        emp1.setWage(25.0);
        assertEquals(25.0, emp1.getPayment().getHourlyRate());
    }


    @Test
    void testEmployeeEquals() {
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

    //-----------FOR GUEST CLASS TESTING--------------
    
    @Test
    void testSetAndGetRoom() {
        guest3.setRoom(standardRoom2);
        assertEquals(standardRoom2, guest3.getRoom(), "Guest's room should be set to the standard room.");
        
        guest3.setRoom(VIPRoom2);
        assertNotEquals("Guest cannot set room because they already have a room.", VIPRoom2, guest.getRoom());
    }

    @Test
    void testAddToBillRoomCharges() {
        guest3.addToBill(100.0, true);
        assertEquals(100.0, guestPayment.getRoomCharges(), "Room charges should be 100.0");
        
        guest3.addToBill(50.0, true);
        assertEquals(150.0, guestPayment.getRoomCharges(), "Room charges should be 150.0");
    }

    @Test
    void testAddToBillAmenityCharges() {
        guest3.addToBill(30.0, false);
        assertEquals(30.0, guestPayment.getAmenityCharges(), "Amenity charges should be 30.0");
        
        guest3.addToBill(20.0, false);

    }

    @Test
    void testGetAmenitiesBooked() {
        List<Amenity> amenities = guest3.getAmenitiesBooked();
        assertTrue(amenities.isEmpty(), "Guest should not have any amenities booked at the start.");

        guest3.getAmenitiesBooked().add(gym);
        assertTrue(guest3.getAmenitiesBooked().contains(gym), "The guest should have gym booked in the list of amaenity.");
    }

    @Test
    void testCheckout() {
        // Assign room and book amenity
        guest3.setRoom(standardRoom2);
        assertTrue(standardRoom2.reserve(guest3), "Room should be reserved");
        guest3.getAmenitiesBooked().add(pool);
        
        // Perform checkout
        guest3.checkout();
        
        // Assertions
        assertNull(guest3.getRoom(), "Guest should have no room assigned after checking out.");
        assertFalse(standardRoom2.isReserved, "The room should not be reserved after.");
        assertTrue(guest3.getAmenitiesBooked().isEmpty(), "The list of booked amenities that guest had should be empty");
    }
        

}
