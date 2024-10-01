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
                        System.out.println("Please select one of the options from the menu.");
                }
            } while (entered < 1 || entered > 4);
            System.out.println("Welcome to the " + h.getName() + "!");
            // user is a Guest
            if (userNum == 1) {
                System.out.println("Select an option (enter a number):\n" +
                        "1. Book a room\n" +
                        "2. Book an amenity\n" +
                        "3. Make a payment\n" +
                        "4. Previous");
                entered = input.nextInt();
                switch (entered) {
                    case 1:
                        System.out.println();
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
        System.out.println("Guest or Employee Login? (Enter 1 for Guest " +
                "portal or 2 for Employee portal):");
        int userNum = input.nextInt();
        while (userNum != 1 && userNum != 2) {
            System.out.println("Invalid entry. Try again.");
            System.out.println("Guest or Employee Login? (Enter 1 for Guest " +
                    "portal or 2 for Employee portal):");
            userNum = input.nextInt();
        }
        return userNum;
    }
}
