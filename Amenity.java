import java.util.ArrayList;

public abstract class Amenity implements Reservable{
    protected String name;
    protected String description;
    protected boolean isAvailable;
    protected int maxOccupancy;
    protected ArrayList<Guest> occupants;

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public boolean getAvailability(){
        return isAvailable;
    }

    public int getMaxOccupancy(){
        return maxOccupancy;
    }

    public ArrayList<Guest> getOccupants(){
        return occupants;
    }

    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }



    //display name, description, and availability of amenity
    public void displayAmenityDetails(){
        System.out.printf("%s\nDescription: %s\nAvailability: %s\n", name, description,
            isAvailable ? "available" : "unavailable");
    }

    //reserve method overridden from Reservable
    @Override
    public void reserve(Guest g){
        occupants.add(g);
    }

    //cancel method overridden from Reservable
    @Override
    public void cancel(Guest g){
        if(occupants.contains(g)){
            occupants.remove(g);
        }else{
            System.out.println("No reservation under the name " + g.getName());
        }
    }


}