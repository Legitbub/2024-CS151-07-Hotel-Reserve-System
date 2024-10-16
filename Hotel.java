import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Random;

public class Hotel {
    private String name;
    private List<Guest> guestList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private List<Manager> managerList = new ArrayList<>();
    private List<Room> openRooms = new ArrayList<>();
    private TreeMap<Room, Guest> roomLog = new TreeMap<>();
    private List<Amenity> amenityLog = new ArrayList<>();
    private double revenue = 0;

    public Hotel() {
        name = "Default Hotel";
    }

    public Hotel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Guest> getGuestList() {
        return guestList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
    public List<Manager> getManagerList() {
        return managerList;
    }
    public List<Room> getOpenRooms() {
        return openRooms;
    }

    public TreeMap<Room, Guest> getRoomLog() {
        return roomLog;
    }

    public List<Amenity> getAmenityLog() {
        return amenityLog;
    }

    public List<Amenity> getOpenAmenities() {
        List<Amenity> openAmenities = new ArrayList<>();
        for(Amenity a : amenityLog){
            if(a.isAvailable)
                openAmenities.add(a);
        }
        return openAmenities;
    }

    public void setEmployeeList(List<Employee> l) {
        employeeList = l;
    }

    public double getRevenue() {
        return revenue;
    }

    public void addEarnings(double revenue) {
        this.revenue += revenue;
    }

    //Add a room to the hotel list
    public void addRooms(Room r) {
        for (Room room : openRooms) {
            if (r.getRoomID() == room.getRoomID()) {
                System.out.println("Room already exists in hotel");
                return;
            }
        }
        openRooms.add(r);
    }

    //Set the list of open rooms
    public void addRooms(List<Room> r) {
        openRooms.addAll(r);
    }

    //Add an amenity to the hotel list
    public void addAmenities(Amenity a) {
        amenityLog.add(a);
    }

    //Add multiple amenities to list
    public void addAmenities(List<Amenity> a) {
        amenityLog.addAll(a);
    }

    //Make a room reservation; update logs
    public void reservation(Room r, Guest g) {
        if(r.reserve(g)){
            openRooms.remove(r);
            roomLog.put(r, g);
            g.setRoom(r);
            applyDiscountToFifthGuest(g);
            g.addToBill(r.getPrice(), true);
            
        }
    }
    //Random give every fifth guest a discount between 10%-20%
    private void applyDiscountToFifthGuest(Guest g){
        if(guestList.size() % 5 == 0){
            Random random = new Random();
            //Chooses a random discount between 10 - 20
            double discountPercentage = 10 + random.nextDouble() * 10;
            System.out.println("Applying a discount of " + discountPercentage + "% for guest " + g.getName());
            g.getRoom().applyDiscount(discountPercentage);
        }
    }

    //Make an amenity reservation; update logs
    public void reservation(Amenity a, Guest g) {
        if (amenityLog.contains(a) && a.isAvailable){
            a.reserve(g);
            g.getAmenitiesBooked().add(a);
            if(a.occupants.size() == a.maxOccupancy){
                a.setAvailable(false);
            }
            g.addToBill(a.getPrice(), false);
            System.out.println(a.getName() +
                    " successfully reserved for " + g.getName());
        } else{
            System.out.println("Amenity is unavailable");
        }
    }

    // Return a string showing the list of available rooms to book
    public String showRooms() {
        String s = "";
        int i = 1;
        for (Room r : openRooms) {
            s += (i + ". Room " + r.roomID + "\n");
            i++;
        }
        return s;
    }

    //Return a string showing the list of booked rooms
    public String bookedRooms() {
        String s = "";
        Object[] rooms = roomLog.keySet().toArray();
        for (int i = 0; i < rooms.length; i++) {
            s += (i + ". Room " + ((Room) rooms[i]).roomID + "\n");
        }
        return s;
    }

    // Return a string showing the list of available amenities to book
    public void showAmenities() {
        int i = 1;
        for (Amenity a : getOpenAmenities()) {
            System.out.print(i + ". ");
            a.displayAmenityDetails();
            i++;
        }
    }

    //Return a string showing the list of booked amenities
    public String bookedAmenities() {
        String s = "";
        for (int i = 1; i <= amenityLog.size(); i++) {
            s += (i + ". " + amenityLog.get(i - 1).getName() + "\n");
        }
        return s;
    }
}
