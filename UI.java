import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        List<Hotel> hotels = buildHotels();
        Scanner input = new Scanner(System.in);
        boolean session = true;
        int userNum = getUserNum(input);
        int entered = 0;
        Hotel h = null;

        while (session) {
            userNum = selectHotel(input, hotels, userNum, h);

            // User is a Guest
            while (userNum == 1) {
                Guest user = assignGuestSession(input, h);
                System.out.println("Select an option (enter a number):\n" +
                        "1. Book a room\n" +
                        "2. Book an amenity\n" +
                        "3. Make a payment\n" +
                        "4. Previous\n" +
                        "5. End session");
                entered = input.nextInt();
                switch (entered) {
                    case 1:
                        System.out.println("Select an available room from " +
                                "the following list (for \\033[3m1. Room " +
                                "200\\033[0m, enter 1");
                        System.out.println(h.showRooms());
                        entered = input.nextInt();
                        Room bookedRoom = h.getOpenRooms().get(entered);
                        h.reservation(bookedRoom, user);
                        System.out.println("Room " + bookedRoom.getRoomID() +
                                " successfully reserved for " + user.getName());
                        break;
                    case 2:
                        System.out.println("Select an available amenity from " +
                                "the following list (enter the number)");
                        h.showAmenities();
                        entered = input.nextInt();
                        Amenity bookedAmenity = h.getOpenAmenities().get(entered);
                        h.reservation(bookedAmenity, user);
                        System.out.println(bookedAmenity.getName() +
                                " successfully reserved for " + user.getName());
                        break;
                    case 3:
                    case 4:
                        userNum = selectHotel(input, hotels, userNum, h);
                    case 5:
                        session = false;
                        userNum = 0;
                        break;
                    default:
                        System.out.println("Invalid entry. Try again.");
                }
            }

            // User is an employee
            while (userNum == 2) {
                System.out.print("Enter employee ID: ");
                entered = input.nextInt();
                Employee search = new Employee(entered);
                if (h.getEmployeeList().contains(search)) {
                    int searchIndex = h.getEmployeeList().indexOf(search);
                    Employee userEmploy = h.getEmployeeList().get(searchIndex);
                    System.out.println("Welcome back " + userEmploy.getName() + "!");
                    System.out.println("Select an option (enter a number):\n" +
                            "1. Book a room\n" +
                            "2. Book an amenity\n" +
                            "3. Modify a booking\n" +
                            "4. Modify employee details\n" +
                            "5. Payment information/Collect paycheck\n" +
                            "6. Previous\n" +
                            "7. End session");
                    entered = input.nextInt();
                    switch (entered) {
                        case 1:
                            Guest guestUser = assignGuestSession(input, h);
                            System.out.println("Select an available room from " +
                                    "the following list (for \\033[3m1. Room " +
                                    "200\\033[0m, enter 1");
                            System.out.println(h.showRooms());
                            entered = input.nextInt();
                            Room bookedRoom = h.getOpenRooms().get(entered);
                            h.reservation(bookedRoom, guestUser);
                            System.out.println("Room " + bookedRoom.getRoomID() +
                                    " successfully reserved for " + guestUser.getName());
                            break;
                        case 2:
                            Guest guestUser2 = assignGuestSession(input, h);
                            System.out.println("Select an available amenity from " +
                                    "the following list (enter the number)");
                            h.showAmenities();
                            entered = input.nextInt();
                            Amenity bookedAmenity = h.getOpenAmenities().get(entered);
                            h.reservation(bookedAmenity, guestUser2);
                            System.out.println(bookedAmenity.getName() +
                                    " successfully reserved for " + guestUser2.getName());
                            break;
                        case 3:
                        case 4:
                            if (!(userEmploy instanceof Supervisor)) {
                                System.out.println("Only supervisors have permission " +
                                        "to change employee details.");
                            } else {

                            }
                            break;
                        case 5:
                        case 6:
                            userNum = selectHotel(input, hotels, userNum, h);
                        case 7:
                            session = false;
                            userNum = 0;
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
        for (int i = 1; i < 501; i++) {
            beachfront.addRooms(new StandardRoom(i));
            grandStreet.addRooms(new StandardRoom(i));
            urbanLife.addRooms(new StandardRoom(i));
        }
        for (int i = 501; i < 1001; i++) {
            beachfront.addRooms(new VIPRoom(i));
            grandStreet.addRooms(new VIPRoom(i));
            urbanLife.addRooms(new VIPRoom(i));
        }

        List<Hotel> hotels = new ArrayList<>();
        hotels.add(beachfront);
        hotels.add(grandStreet);
        hotels.add(urbanLife);
        return hotels;
    }

    public static int getUserNum(Scanner input) {
        System.out.print("Guest or Employee Login? (Enter 1 for Guest " +
                "portal or 2 for Employee portal): ");
        int userNum = input.nextInt();
        while (userNum != 1 && userNum != 2) {
            System.out.println("Invalid entry. Try again.");
            System.out.print("Guest or Employee Login? (Enter 1 for Guest " +
                    "portal or 2 for Employee portal): ");
            userNum = input.nextInt();
        }
        return userNum;
    }

    // Prompt the user to select one of the hotels from the list
    // If the user selects "previous", the method will call
    // getUserNum to change the user session number and then continue
    // until a hotel is selected
    // Returns the user number for the initial method call
    public static int selectHotel(Scanner input, List<Hotel> hotels,
                                  int userNum, Hotel h) {
        int entered;
        do {
            System.out.println("Select your hotel (enter a number):\n" +
                    "1. Beachfront\n" +
                    "2. Grand Street\n" +
                    "3. Urban Life\n" +
                    "4. Previous");
            entered = input.nextInt();
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
            System.out.println("Welcome back " + name + "!");
            returning = true;
        }
        if (!returning) {
            h.getGuestList().add(user);
        }
        return user;
    }
}
