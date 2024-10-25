import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {
    public static Hotel h = null;

    public static void main(String[] args) {
        List<Hotel> hotels = buildHotels();
        Scanner input = new Scanner(System.in);
        boolean session = true;
        int userNum = getUserNum(input);
        int entered = 0;

        while (session) {
            userNum = selectHotel(input, hotels, userNum);

            // User is a Guest
            while (userNum == 1) {
                // Check if the hotel is full
                if (h.getGuestList().size() >= 100) {
                    System.out.println("Hotel is full, select a different hotel.");
                    userNum = selectHotel(input, hotels, userNum);
                    continue; // Skip to next iteration if full
                }
                System.out.print("New Guest session initiated. ");
                Guest user = assignGuestSession(input, h);

                while (true) {
                    System.out.println("Select an option (enter a number):\n" +
                            "1. Book a room\n" +
                            "2. Book an amenity\n" +
                            "3. Make a payment\n" +
                            "4. Leave a rating\n" +
                            "5. Call room service\n" +
                            "6. Previous\n" +
                            "7. End session");

                    // Validate user input
                    while (true) {
                        try {
                            entered = input.nextInt();
                            input.nextLine(); // Consume new line
                            break; // Exit loop

                        } catch (InputMismatchException e) {
                            System.out.println("Invalid entry. Try again.");
                            input.nextLine();
                        }
                    }

                    switch (entered) {
                        case 1: // Book a room
                            Room bookedRoom = roomSelect(input, h, false);
                            h.reservation(bookedRoom, user);
                            break;
                        case 2:
                            Amenity bookedAmenity = amenitySelect(input, h, false);
                            h.reservation(bookedAmenity, user);
                            break;
                        case 3:
                            user.displayGuestAccount(input, h);
                            break;
                        case 4:
                            leaveRating(input, user);
                            break;
                        case 5:
                            Room r = roomSelect(input, h, true);
                            if (r != null) {
                                r.callRoomService();
                            }
                            break;
                        case 6:
                            userNum = selectHotel(input, hotels, userNum);
                            break;
                        case 7:
                            session = false;
                            userNum = 0;
                            endSession(input);
                        default:
                            System.out.println("Invalid entry. Try again.");
                    }
                }

            }

            // User is an employee
            while (userNum == 2) {
                employeeSession(input, h, userNum, hotels);
            }

        }

    }

    // --------------------------------------------------------------------------------------------------
    // Main employee session method
    public static void employeeSession(Scanner input, Hotel h, int userNum, List<Hotel> hotels) {
        System.out.println("Enter employee ID: ");
        String ID = input.nextLine();
        Employee search = new Employee(ID);

        if (!h.getEmployeeList().contains(search)) {
            System.out.println("Invalid Employee ID. Try again.");
            return;
        }

        Employee userEmploy = getEmployeeById(h, ID);
        System.out.println("Welcome back " + userEmploy.getName() + "!");

        boolean session = true;

        while (session && userNum == 2) {
            int option = getValidatedIntInput(input, """
                    Select an option (enter a number):
                    1. Book a room
                    2. Book an amenity
                    3. Modify a booking
                    4. Modify employee/hotel details
                    5. Log hours/Paycheck information
                    6. Previous
                    7. End session
                    """);

            switch (option) {
                case 1:
                    handleRoomBooking(input, h, assignGuestSession(input, h));
                case 2:
                    handleAmenityBooking(input, h, assignGuestSession(input, h));
                case 3:
                    handleModifyBooking(input, h, userEmploy);
                case 4:
                    handleEmployeeModification(input, h, userEmploy);
                case 5:
                    handlePayroll(input, userEmploy);
                case 6:
                    userNum = selectHotel(input, hotels, userNum);
                case 7:
                    endSession(input);
                default:
                    System.out.println("Invalid entry. Try again.");
            }

        }
    }

    // Helper method to end session
    public static void endSession(Scanner input) {
        System.out.println("Ending session...");
        input.close();
        System.exit(1);
    }

    // Helper method to retrieve employee by ID
    public static Employee getEmployeeById(Hotel h, String ID) {
        for (Employee e : h.getEmployeeList()) {
            if (e.getId().equals(ID)) {
                return e;
            }
        }
        return null; // If no matching employee found
    }

    // Helper to handle room booking
    public static void handleRoomBooking(Scanner input, Hotel h, Guest guest) {
        Room bookedRoom = roomSelect(input, h, false);

        if (bookedRoom != null) {
            h.reservation(bookedRoom, guest);

        } else {
            System.out.println("Room booking failed.");
        }
    }

    // Method to handle amenity booking
    public static void handleAmenityBooking(Scanner input, Hotel h, Guest guest) {
        Amenity bookedAmenity = amenitySelect(input, h, false);
        if (bookedAmenity != null) {
            h.reservation(bookedAmenity, guest);

        } else {
            System.out.println("Amenity booking failed.");
        }
    }

    // Method to handle the modify booking
    public static void handleModifyBooking(Scanner input, Hotel h, Employee userEmploy) {
        int bookingType = getValidatedIntInput(input, "Modify Room or Amenity bookings?:\n1. Room\n2. Amenity");

        if (bookingType == 1) {
            modifyRoomBooking(input, h, userEmploy);

        } else if (bookingType == 2) {
            modifyAmenityBooking(input, h, userEmploy);

        } else {
            System.out.println("Invalid entry.");
        }
    }

    // Method to modify room booking
    public static void modifyRoomBooking(Scanner input, Hotel h, Employee userEmploy) {
        Room modifiedRoom = roomSelect(input, h, true);
        if (modifiedRoom == null)
            return;

        int action = getValidatedIntInput(input, "Cancel or switch booking? (Enter 1 for cancel or 2 for switch): ");

        if (action == 1) {
            userEmploy.modifyRoom(modifiedRoom);

        } else if (action == 2) {
            Guest newGuest = assignGuestSession(input, h);
            userEmploy.modifyRoom(modifiedRoom, newGuest, h);

        } else {
            System.out.println("Invalid entry.");
        }
    }

    // Method to modify amenity booking
    public static void modifyAmenityBooking(Scanner input, Hotel h, Employee userEmploy) {
        Amenity modifiedAmenity = amenitySelect(input, h, true);
        if (modifiedAmenity == null)
            return;

        int action = getValidatedIntInput(input, "Cancel or switch booking? (Enter 1 for cancel or 2 for switch): ");

        if (action == 1) {
            System.out.print("Whose reservation will be cancelled?: ");
            String guestName = input.nextLine();
            userEmploy.modifyAmenity(modifiedAmenity, new Guest(guestName));

        } else if (action == 2) {
            Guest newGuest = assignGuestSession(input, h);
            System.out.print("Whose reservation will be switched?: ");
            String guestName = input.nextLine();
            userEmploy.modifyAmenity(modifiedAmenity, new Guest(guestName), newGuest, h);

        } else {
            System.out.println("Invalid entry.");
        }
    }

    // Method to handle employee modification
    public static void handleEmployeeModification(Scanner input, Hotel h, Employee userEmploy) {
        if (userEmploy instanceof Manager manager) {
            manager.managerModify(input, h);

        } else {
            System.out.println("Only supervisors have permission to change employee details.");
        }
    }

    // Method to handle pay roll
    public static void handlePayroll(Scanner input, Employee userEmploy) {
        int payrollOption = getValidatedIntInput(input, "Select an option:\n1) Log hours\n2) Check timecard");

        if (payrollOption == 1) {
            System.out.print("Add how many hours to payroll?: ");
            double hours = input.nextDouble();
            input.nextLine();
            userEmploy.logHours(hours);

        } else if (payrollOption == 2) {
            userEmploy.displayEmployeeDetails();
            System.out.println("Get ready for that paycheck - $coming soon!$");

        } else {
            System.out.println("Invalid entry.");
        }
    }

    // Method to get validated input
    public static int getValidatedIntInput(Scanner input, String prompt) {
        int entered;
        while (true) {
            System.out.print(prompt);
            try {
                entered = input.nextInt();
                input.nextLine();
                return entered;
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please enter a number.");
                input.nextLine();
            }
        }
    }
    // ----------------------------------------------------------------------------------------------------------------------

    public static List<Hotel> buildHotels() {
        Hotel beachfront = new Hotel("Beachfront");
        Hotel grandStreet = new Hotel("Grand Street");
        Hotel urbanLife = new Hotel("Urban Life");
        for (int i = 1; i < 51; i++) {
            beachfront.addRooms(new StandardRoom(i));
            grandStreet.addRooms(new StandardRoom(i));
            urbanLife.addRooms(new StandardRoom(i));
        }
        for (int i = 51; i < 101; i++) {
            beachfront.addRooms(new VIPRoom(i));
            grandStreet.addRooms(new VIPRoom(i));
            urbanLife.addRooms(new VIPRoom(i));
        }

        List<Employee> workers = new ArrayList<>();
        Employee rick = new Employee("001", "Rick", "Guest Services", 22);
        Employee cheri = new Employee("002", "Cheri", "Accounts", 35);
        Employee bonzo = new Manager("003", "Bonzo", "Manager", 80);
        workers.add(rick);
        workers.add(cheri);
        workers.add(bonzo);
        beachfront.setEmployeeList(workers);
        grandStreet.setEmployeeList(workers);
        urbanLife.setEmployeeList(workers);

        List<Amenity> amenities = new ArrayList<>();
        Buffet buffet = new Buffet("Le Food Buffet", "Eat until you KABOOM!",
                75, "Dinner");
        Gym gym = new Gym("Buff Dungeon", "A gym for real bros", 15);
        Pool pool = new Pool("The Ocean", "It's a really big pool",
                10, 10000, 2000, 50000, 85);
        amenities.add(buffet);
        amenities.add(gym);
        amenities.add(pool);
        beachfront.addAmenities(amenities);
        grandStreet.addAmenities(amenities);
        urbanLife.addAmenities(amenities);

        List<Hotel> hotels = new ArrayList<>();
        hotels.add(beachfront);
        hotels.add(grandStreet);
        hotels.add(urbanLife);
        return hotels;
    }

    public static int getUserNum(Scanner input) {
        boolean correct = false;
        int userNum = 0;
        while (!correct && userNum != 1 && userNum != 2) {
            try {
                System.out.print("Guest or Employee Login? (Enter 1 for Guest " +
                        "portal or 2 for Employee portal): ");
                userNum = input.nextInt();
                if (userNum != 1 && userNum != 2) {
                    System.out.println("Invalid entry. Try again.");
                } else {
                    correct = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Try again.");
                input.nextLine();
            }
        }
        input.nextLine();
        return userNum;
    }

    // Prompt the user to select one of the hotels from the list
    // If the user selects "previous", the method will call
    // getUserNum to change the user session number and then continue
    // until a hotel is selected
    // Returns the user number for the initial method call
    public static int selectHotel(Scanner input, List<Hotel> hotels,
            int userNum) {
        int entered = 0;
        do {
            System.out.println("Select your hotel (enter a number):\n" +
                    "1. Beachfront\n" +
                    "2. Grand Street\n" +
                    "3. Urban Life\n" +
                    "4. Previous");
            boolean correct = false;
            while (!correct) {
                try {
                    entered = input.nextInt();
                    correct = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid entry. Try again.");
                    input.nextLine();
                }
            }

            switch (entered) {
                case 1:
                    h = hotels.get(0);
                    break;
                case 2:
                    h = hotels.get(1);
                    break;
                case 3:
                    h = hotels.get(2);
                    break;
                case 4:
                    userNum = getUserNum(input);
                    break;
                default:
                    System.out.println("Please select one of the " +
                            "options from the menu.");
            }
        } while (entered < 1 || entered >= 4);

        System.out.println("Welcome to the " + h.getName() + "!");
        return userNum;
    }

    public static Guest assignGuestSession(Scanner input, Hotel h) {
        boolean returning = false;
        System.out.print("Whose name will be on the booking?: ");
        String name = input.nextLine();
        Guest user = new Guest(name);
        if (h.getGuestList().contains(user)) {
            int index = h.getGuestList().indexOf(user);
            user = h.getGuestList().get(index);
            System.out.print("Welcome back " + name + "!");
            returning = true;
        }
        if (!returning) {
            h.getGuestList().add(user);
            System.out.print("Welcome " + name + "!");
        }
        input.nextLine();
        return user;
    }

    // Returns a room for the user to book or modify, depending
    // on the boolean parameter
    public static Room roomSelect(Scanner input, Hotel h, boolean booked) {
        Room r = null;
        int entered = 0;

        if (!booked) {
            do {
                System.out.println("Select an available room from " +
                        "the following list (for 1. Room " +
                        "200, enter 1):");
                System.out.println(h.showRooms());
                entered = getValidRoomSelection(input, h.getOpenRooms().size());

                if (entered >= 0) {
                    r = h.getOpenRooms().get(entered);
                } else {
                    System.out.println("Invalid entry. Try again.");
                }
            } while (entered < 0);

        } else {
            // selecting booked room
            if (h.getRoomLog().size() > 0) {
                do {
                    System.out.println("Select a booked room from " +
                            "the following list (for 1. Room " +
                            "200, enter 1):");
                    System.out.println(h.bookedRooms());
                    entered = getValidRoomSelection(input, h.getRoomLog().size());

                    if (entered >= 0) {
                        r = (Room) h.getRoomLog().keySet().toArray()[entered];
                    } else {
                        System.out.println("Invalid entry. Try again.");
                    }

                } while (entered < 0);
            } else {
                System.out.println("No rooms are booked at the moment.");
            }
        }
        input.nextLine();
        return r;
    }

    // Method to get valid integer input for room selection
    public static int getValidRoomSelection(Scanner input, int roomCount) {
        int selection = -1;
        boolean valid = false;

        while (!valid) {
            try {
                selection = input.nextInt() - 1;

                if (selection >= 0 && selection < roomCount) {
                    valid = true;
                } else {
                    System.out.println("Invalid room number. Try again.");
                    selection = -1; // Reset
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please enter a number.");
                input.nextLine();
            }
        }
        return selection;
    }

    // Returns an amenity for the user to book or modify, depending
    // on the boolean parameter
    public static Amenity amenitySelect(Scanner input, Hotel h, boolean booked) {
        Amenity a = null;
        int entered = 0;
        if (!booked) {
            do {
                System.out.println("Select an available amenity from " +
                        " the following list (enter the number): ");
                h.showAmenities();
                boolean correct = false;
                while (!correct) {
                    try {
                        entered = input.nextInt() - 1;
                        correct = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry. Try again.");
                        input.nextLine();
                    }
                }
                if (entered >= 0 && entered < h.getOpenAmenities().size()) {
                    a = h.getOpenAmenities().get(entered);
                } else {
                    System.out.println("Invalid entry. Try again.");
                }
            } while (entered < 0 || entered >= h.getOpenAmenities().size());
        } else {
            if (h.getAmenityLog().size() > 0) {
                do {
                    System.out.println("Select a booked amenity from" +
                            "the following list (enter the number)");
                    System.out.println(h.bookedAmenities());
                    boolean correct = false;
                    while (!correct) {
                        try {
                            entered = input.nextInt() - 1;
                            correct = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid entry. Try again.");
                            input.nextLine();
                        }
                    }
                    if (entered >= 0 && entered < h.getAmenityLog().size()) {
                        a = h.getAmenityLog().get(entered);
                    } else {
                        System.out.println("Invalid entry. Try again.");
                    }
                } while (entered < 0 || entered >= h.getAmenityLog().size());
            } else {
                System.out.println("No amenities are booked at the moment.");
            }
        }
        input.nextLine();
        return a;
    }

    // Method to handle rating logic
    public static void leaveRating(Scanner input, Guest user) {
        if (user.getAmenitiesBooked().isEmpty()) {
            System.out.println("Cannot add rating without " +
                    "outstanding reservation");
            return; // Exit if no booked amenities
        }

        // Display amenities for rating
        System.out.println("Which amenity will you rate?:");
        displayAmenities(user);

        Amenity rated = selectAmenity(input, user);

        if (rated == null) {
            return; // Exit if selection invalid
        }

        // Handle rating input
        int rating = getRating(input);

    }

    // -------------------------------------------------------------------------------------------------------
    // Method to display booked amenities
    public static void displayAmenities(Guest user) {
        for (int i = 1; i <= user.getAmenitiesBooked().size(); i++) {
            System.out.println(i + ". " + user.getAmenitiesBooked().get(i - 1).getName());
        }
    }

    // Method to select an amenity
    public static Amenity selectAmenity(Scanner input, Guest user) {
        int entered = -1;

        while (true) {
            try {
                entered = input.nextInt() - 1;
                return user.getAmenitiesBooked().get(entered);
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println("Invalid entry. Try again.");
                input.nextLine();
            }
        }
    }

    // Method to get a valid rating from the user
    public static int getRating(Scanner input) {
        int entered = -1;

        while (true) {
            System.out.println("How would you rate this amenity (from 1 - 10)?:");

            try {
                entered = input.nextInt();

                if (entered >= 1 && entered <= 10) {
                    return entered; // Return valid rating
                } else {
                    System.out.println("Please enter a number between 1 and 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please enter a number between 1 and 10.");
                input.nextLine();
            }
        }
    }
    // -----------------------------------------------------------------------------
}
