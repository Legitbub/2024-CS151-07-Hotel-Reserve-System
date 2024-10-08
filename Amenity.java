import java.util.ArrayList;

public abstract class Amenity implements Reservable {
    public String name;
    public String description;
    public boolean isAvailable;
    public int maxOccupancy;
    public ArrayList<Guest> occupants;

    //Method to set amenity's availability
    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public void displayAmenityDetails(){
        System.out.printf("%s\nDescription: %s\nAvailability: %s\n", name, description,
            isAvailable ? "available" : "unavailable");
    }

    public String getName() {
        return name;
    }

    @Override
    public void reserve(Guest guest){
        if(isAvailable){
            occupants.add(guest);
            if(occupants.size() == maxOccupancy)
                isAvailable = false;
        }else{
            System.out.println("Amenity unavailable");
        }

    }
}