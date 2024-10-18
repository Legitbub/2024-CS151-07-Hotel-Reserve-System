
import java.util.ArrayList;
import java.util.List;
public abstract class Room implements Reservable, Comparable<Room> {
    protected int roomID;
    protected String level;
    protected Guest guest;
    protected double price;
    protected boolean isReserved = false;
    protected List<Guest> occupantHistory = new ArrayList<>();
    private static final double ROOM_SERVICE_PRICE = 30.0; // Fixed room service price

    

    @Override
    public boolean reserve(Guest g) {
        if (!isReserved) {
            guest = g;
            isReserved = true;
            System.out.println("Room " + roomID +
                    " successfully reserved for " + g.getName());
            return true;
        } else {
            System.out.println("Room " + roomID + " already reserved");
            return false;
        }
    }

    @Override
    public boolean cancel(Guest g) {
        System.out.println(g.getName() + " checked out of room " + roomID);
        addOccupantToHistory(guest);
        return true;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    @Override
    public int compareTo(Room o) {
        if (o == null) {
            return 0;
        } else if (roomID < o.getRoomID()) {
            return -1;
        } else if (roomID > o.getRoomID()) {
            return 1;
        } else {
            return 0;
        }
    }

    //Will be called for every fifth Guest.
    public void applyDiscount(double discountPercentage) {
        if (discountPercentage > 0 && discountPercentage <= 100) {
            double discountAmount = (price * discountPercentage) / 100;
            price -= discountAmount;
            System.out.println("Discount applied. New price for room " + roomID + " is $" + price);
        } else {
            System.out.println("Invalid discount percentage.");
        }
    }
    //See a list of all the guest who previously booked the room
    public void displayOccupantHistory() {
        if (occupantHistory.isEmpty()) {
            System.out.println("No occupant history for Room ID: " + roomID);
            return;
        }

        System.out.println("Occupant History for Room ID: " + roomID);
        for (Guest guest : occupantHistory) {
            System.out.println("- " + guest.getName());
        }
    }

    //Add the guest name to room's history when they checkout.
    private void addOccupantToHistory(Guest guest){
        occupantHistory.add(guest);
        System.out.println(guest.getName() + " added to occupant history of room " + roomID);

    }

    // Method to call room service and add charges whenever guest call for room service
    public void callRoomService() {
        if (guest != null) { 
            guest.addToBill(ROOM_SERVICE_PRICE, true); // Add room service charges to the guest's bill under room charges
            System.out.println(guest.getName() + " called room service for an amount of $" + ROOM_SERVICE_PRICE);
        } else {
            System.out.println("No guest in room " + roomID + " to call room service.");
        }
    }
}
