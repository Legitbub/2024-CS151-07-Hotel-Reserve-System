import java.util.ArrayList;

public abstract class Amenity implements Reservable{
    protected String name;
    protected String description;
    protected boolean isAvailable;
    protected double price;
    protected int maxOccupancy;
    protected ArrayList<Guest> occupants = new ArrayList<>();
    protected ArrayList<Employee> employees = new ArrayList<>();
    private static final int DEFAULT_MAX_OCCUPANCY = 30;

    public Amenity(){
        name = "no name";
        description = "no description";
        isAvailable  = true;
        maxOccupancy = DEFAULT_MAX_OCCUPANCY;
    }

    public Amenity(String name, String description, double price){
        this.name = name;
        this.description = description;
        isAvailable = true;
        maxOccupancy = DEFAULT_MAX_OCCUPANCY;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public boolean getAvailability(){
        return isAvailable;
    }

    public double getPrice() {
        return price;
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

    //reserve method overridden from Reservable, returns true if reservation is successful, false if not
    @Override
    public boolean reserve(Guest g){
        if(isAvailable){
            occupants.add(g);
            if(occupants.size() == maxOccupancy){
                isAvailable = false;
            }
            return true;
        }else{
            System.out.println("Amenity is unavailable");
            return false;
        }
    }

    //cancel method overridden from Reservable
    @Override
    public boolean cancel(Guest g){
        if (occupants.contains(g)) {
            occupants.remove(g);
            g.getAmenitiesBooked().remove(this);
            System.out.println("Cancelled " + name + " booking for " +
                    g.getName() + ".");
            if (!isAvailable) {
                isAvailable = true;
            }
            return true;
        } else {
            System.out.println("No booking found for " + g.getName());
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return name.equals(((Amenity) obj).getName());
    }

}