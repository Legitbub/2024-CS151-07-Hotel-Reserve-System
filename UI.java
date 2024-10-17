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
                System.out.print("New Guest session initiated. ");
                Guest user = assignGuestSession(input, h);
                System.out.println("Select an option (enter a number):\n" +
                        "1. Book a room\n" +
                        "2. Book an amenity\n" +
                        "3. Make a payment\n" +
                        "4. Previous\n" +
                        "5. End session");
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
                input.nextLine();
                switch (entered) {
                    case 1:
                        Room bookedRoom = roomSelect(input, h, false);
                        h.reservation(bookedRoom, user);
                        break;
                    case 2:
                        Amenity bookedAmenity = amenitySelect(input, h, false);
                        h.reservation(bookedAmenity, user);
                        break;
                    case 3:
                        user.displayGuestAccount(input, h);
                    case 4:
                        userNum = selectHotel(input, hotels, userNum);
                        break;
                    case 5:
                        session = false;
                        userNum = 0;
                        input.close();
                        break;
                    default:
                        System.out.println("Invalid entry. Try again.");
                }
            }

            // User is an employee
            while (userNum == 2) {
                System.out.print("Enter employee ID: ");
                String ID = input.nextLine();
                Employee search = new Employee(ID);
                if (h.getEmployeeList().contains(search)) {
                    int searchIndex = h.getEmployeeList().indexOf(search);
                    Employee userEmploy = h.getEmployeeList().get(searchIndex);
                    System.out.println("Welcome back " + userEmploy.getName() + "!");
                    System.out.println("Select an option (enter a number):\n" +
                            "1. Book a room\n" +
                            "2. Book an amenity\n" +
                            "3. Modify a booking\n" +
                            "4. Modify employee/hotel details\n" +
                            "5. Log hours/Paycheck information\n" +
                            "6. Previous\n" +
                            "7. End session");
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
                    input.nextLine();
                    switch (entered) {
                        case 1:
                            Guest guestUser = assignGuestSession(input, h);
                            Room bookedRoom = roomSelect(input, h, false);
                            h.reservation(bookedRoom, guestUser);
                            break;
                        case 2:
                            Guest guestUser2 = assignGuestSession(input, h);
                            Amenity bookedAmenity = amenitySelect(input, h, false);
                            h.reservation(bookedAmenity, guestUser2);
                            break;
                        case 3:
                            do {
                                System.out.println("Modify Room or Amenity bookings?:\n" +
                                        "1. Room\n2. Amenity");
                                entered = input.nextInt();
                                if (entered != 1 && entered != 2) {
                                    System.out.println("Invalid entry. Enter 1 for " +
                                            "Room or 2 for Amenity.");
                                }
                            } while (entered != 1 && entered != 2);

                            // Modify a room booking
                            if (entered == 1) {
                                Room modifiedRoom = roomSelect(input, h, true);
                                if (modifiedRoom != null) {
                                    do {
                                        System.out.print("Cancel or switch booking? " +
                                                "(Enter 1 for cancel or 2 for switch): ");
                                        entered = input.nextInt();
                                        if (entered != 1 && entered != 2) {
                                            System.out.println("Invalid entry. Enter 1 for " +
                                                    "cancel or 2 for switch.");
                                        }
                                    } while (entered != 1 && entered != 2);
                                    if (entered == 1) {
                                        userEmploy.modifyRoom(modifiedRoom);
                                    } else {
                                        Guest newG = assignGuestSession(input, h);
                                        userEmploy.modifyRoom(modifiedRoom, newG, h);
                                    }
                                }
                            } else {                            // Modify an amenity booking
                                Amenity modifiedAmenity = amenitySelect(input, h, true);
                                if (modifiedAmenity != null) {
                                    do {
                                        System.out.println("Cancel or switch booking? " +
                                                "(Enter 1 for cancel or 2 for switch): ");
                                        boolean correct2 = false;
                                        while (!correct2) {
                                            try {
                                                entered = input.nextInt();
                                                correct2 = true;
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid entry. Try again.");
                                                input.nextLine();
                                            }
                                        }
                                        if (entered != 1 && entered != 2) {
                                            System.out.println("Invalid entry. Enter 1 for " +
                                                    "cancel or 2 for switch.");
                                        }
                                    } while (entered != 1 && entered != 2);
                                    input.nextLine();
                                    System.out.println("Whose " + modifiedAmenity.getName() +
                                            " reservation will be cancelled?: ");
                                    String cancelled = input.nextLine();
                                    Guest c = new Guest(cancelled);
                                    if (entered == 1) {
                                        userEmploy.modifyAmenity(modifiedAmenity, c);
                                    } else {
                                        Guest newG = assignGuestSession(input, h);
                                        userEmploy.modifyAmenity(modifiedAmenity, c, newG, h);
                                    }
                                }
                            }
                            break;
                        case 4:
                            if (!(userEmploy instanceof Manager)) {
                                System.out.println("Only supervisors have permission " +
                                        "to change employee details.");
                            } else {
                                ((Manager) userEmploy).managerModify(input, h);
                            }
                            break;
                        case 5:
                            do {
                                System.out.println("Select an option:\n1) " +
                                        "Log hours\n2) Check timecard");
                                entered = input.nextInt();
                                if (entered != 1 && entered != 2) {
                                    System.out.println("Please enter 1 or 2 for the below options.");
                                }
                            } while (entered != 1 && entered != 2);
                            input.nextLine();

                            if (entered == 1) {
                                System.out.println("Add how many hours to payroll?: ");
                                double hours = input.nextDouble();
                                input.nextLine();
                                userEmploy.logHours(hours);
                            } else {
                                userEmploy.displayEmployeeDetails();
                                System.out.println("Get ready for that paycheck - $coming soon!$");
                            }
                            break;
                        case 6:
                            userNum = selectHotel(input, hotels, userNum);
                            break;
                        case 7:
                            session = false;
                            userNum = 0;
                            input.close();
                            break;
                        default:
                            System.out.println("Invalid entry. Try again.");
                    }
                } else {
                    System.out.println("Invalid Employee ID. Try again.");
                }
            }
        }
    }

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
                75, Buffet.MenuType.DINNER);
        Gym gym = new Gym("Buff Dungeon", "A gym for real bros", 15);
        Pool pool = new Pool("The Ocean", "It's a really big pool",
                10, 10000, 2000, 50000);
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
        input.nextLine();
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
                if (entered >= 0 && entered < h.getOpenRooms().size()) {
                    r = h.getOpenRooms().get(entered);
                } else {
                    System.out.println("Invalid entry. Try again.");
                }
            } while (entered < 0 || entered >= h.getOpenRooms().size());

        } else {
            if (h.getRoomLog().size() > 0) {
                do {
                    System.out.println("Select a booked room from " +
                            "the following list (for 1. Room " +
                            "200, enter 1):");
                    System.out.println(h.bookedRooms());
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
                    if (entered >= 0 && entered < h.getRoomLog().size()) {
                        r = (Room) h.getRoomLog().keySet().toArray()[entered];
                    } else {
                        System.out.println("Invalid entry. Try again.");
                    }
                } while (entered < 0 && entered >= h.getRoomLog().size());
            } else {
                System.out.println("No rooms are booked at the moment.");
            }
        }
        input.nextLine();
        return r;
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
}
