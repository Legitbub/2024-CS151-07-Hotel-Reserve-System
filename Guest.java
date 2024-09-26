import java.util.ArrayList;

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
        room = null;
        amenitiesBooked.clear();
    }
}
