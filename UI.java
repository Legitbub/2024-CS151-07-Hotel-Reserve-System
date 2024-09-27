import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean session = true;
        System.out.println("Guest or Employee Login? (Enter 1 for Guest " +
                "portal or 2 for Employee portal):");
        int userNum = input.nextInt();
        while (userNum != 1 && userNum != 2) {
            System.out.println("Invalid entry. Try again.");
            System.out.println("Guest or Employee Login? (Enter 1 for Guest " +
                    "portal or 2 for Employee portal):");
            userNum = input.nextInt();
        }

        while (session) {
            // user is a Guest
            if (userNum == 1) {
                System.out.println("Select an option (enter 1, 2, or 3):\n" +
                        "1. Book a room\n" +
                        "2. Book an amenity\n" +
                        "3. Make a payment");
            }
        }
    }
}
