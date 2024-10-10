import java.util.ArrayList;
import java.util.List;

public class Guest {
    private String name;
    private Room room;
    private double rewardsPoints;
    private List<Amenity> amenitiesBooked = new ArrayList<Amenity>();

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

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getRewardsPoints() {
        return rewardsPoints;
    }

    public void setRewardsPoints(double rewardsPoints) {
        this.rewardsPoints = rewardsPoints;
    }

    public List<Amenity> getAmenitiesBooked() {
        return amenitiesBooked;
    }

    //Resets booking status
    public void checkout() {
        room.isReserved = false;
        room.guest = null;
        room = null;
        amenitiesBooked.clear();
    }

    public void displayGuestAccount() {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return name.equals(((Guest) obj).getName());
    }
}
