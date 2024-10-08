import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hotel {
    private String name;
    private List<Guest> guestList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private List<Room> openRooms = new ArrayList<>();
    private HashMap<Room, Guest> roomLog = new HashMap<>();
    private List<Amenity> amenityLog = new ArrayList<>();

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

    public HashMap<Room, Guest> getRoomLog() {
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
        if(!r.isReserved){
            openRooms.remove(r);
            roomLog.put(r, g);
            r.reserve(g);
            g.setRoom(r);
        }else{
            System.out.println("Room " + r.roomID + " already reserved");
        }

    }

    //Make an amenity reservation; update logs
    public void reservation(Amenity a, Guest g) {
        if(amenityLog.contains(a) && a.isAvailable){
            a.reserve(g);
            g.getAmenitiesBooked().add(a);
            if(a.occupants.size() == a.maxOccupancy){
                a.setAvailable(false);
            }
        }else{
            System.out.println("Amenity is unavailable");
        }
    }

    public void cancelReservation(Amenity a, Guest g){
        if(a.occupants.contains(g)){
            a.cancel(g);
            amenityLog.remove(a);
            if(!a.isAvailable)
                a.isAvailable = true;
        }else{
            System.out.println("No reservation under the name " + g.getName());
        }
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
        for (Amenity a : getOpenAmenities()) {
            System.out.print(i + ". ");
            a.displayAmenityDetails();
            i++;
        }
    }
}
