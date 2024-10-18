import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Guest {
    private String name;
    private Room room;
    private List<Amenity> amenitiesBooked = new ArrayList<Amenity>();
    private GuestPayment payment = new GuestPayment();

    public Guest() {

    }

    public Guest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setRoom(Room room) {
        if(this.getRoom() == null){
            this.room = room;
        }
    }
    

    public void addToBill(double charges, boolean room) {
        if (room) {
            payment.addRoomCharges(charges);
        } else {
            payment.addAmenityCharges(charges);
        }
    }

    public List<Amenity> getAmenitiesBooked() {
        return amenitiesBooked;
    }

    //Resets booking status
    public void checkout() {
        room.cancel(this);
        room.isReserved = false;
        room.guest = null;
        room = null;
        amenitiesBooked.clear();
    }

    public void displayGuestAccount(Scanner input, Hotel h) {
        System.out.printf("Room charges: $%.2f\n", payment.getRoomCharges());
        System.out.printf("Amenity charges: $%.2f\n", payment.getAmenityCharges());
        System.out.println("Available rewards points: " + payment.getLoyaltyPoints());
        System.out.print("Pay bill? (Enter \"yes\" for yes, anything else for no): ");
        String pay = input.nextLine().toLowerCase();
        if (pay.equals("yes")) {
            payment.processPayment(h);
        } else {
            System.out.print("Press enter to exit payment system.");
        }
        input.nextLine();
    }

    public void begone(Hotel h) {
        h.getGuestList().remove(this);
        System.out.println("R.I.P.\nThey were never here...");
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return name.equals(((Guest) obj).getName());
    }
}
