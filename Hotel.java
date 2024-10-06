import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hotel {
    private String name;
    private List<Guest> guestList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private List<Room> openRooms = new ArrayList<>();
    private List<Amenity> openAmenities = new ArrayList<>();
    private HashMap<Room, Guest> roomLog = new HashMap<>();
    private HashMap<Amenity, Guest> amenityLog = new HashMap<>();

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
    public List<Room> getOpenRooms() {
        return openRooms;
    }

    public List<Amenity> getOpenAmenities() {
        return openAmenities;
    }

    public HashMap<Room, Guest> getRoomLog() {
        return roomLog;
    }

    public HashMap<Amenity, Guest> getAmenityLog() {
        return amenityLog;
    }

    public void setEmployeeList(List<Employee> l) {
        employeeList = l;
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
        openAmenities.add(a);
    }

    //Set the list of open amenities
    public void addAmenities(List<Amenity> a) {
        openAmenities.addAll(a);
    }

    //Make a room reservation; update logs
    public void reservation(Room r, Guest g) {
        openRooms.remove(r);
        roomLog.put(r, g);
        r.reserve(g);
        g.setRoom(r);
    }

    //Make an amenity reservation; update logs
    public void reservation(Amenity a, Guest g) {
        openAmenities.remove(a);
        amenityLog.put(a, g);
        a.reserve(g);
        g.getAmenitiesBooked().add(a);
    }

    //Return a string showing the list of available rooms to book
    public String showRooms() {
        String s = "";
        int i = 1;
        for (Room r : openRooms) {
            s += (i + ". Room " + r.roomID + "\n");
        }
        return s;
    }

    //Return a string showing the list of available amenities to book
    public void showAmenities() {
        int i = 1;
        for (Amenity a : openAmenities) {
            System.out.print(i + ". ");
            a.displayAmenityDetails();
            i++;
        }
    }
}
