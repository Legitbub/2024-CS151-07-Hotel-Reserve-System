import java.util.ArrayList;

public abstract class Amenity implements Reservable{
    protected String name;
    protected String description;
    protected boolean isAvailable;
    protected int maxOccupancy;
    protected ArrayList<Integer> ratings = new ArrayList<>();
    protected ArrayList<Guest> occupants = new ArrayList<>();
    protected int totalReservations;
    protected float basePrice;
    protected float currentPrice;
    private static final int DEFAULT_BASE_PRICE = 15;
    private static final int DEFAULT_MAX_OCCUPANCY = 30;

    public Amenity(){
        name = "no name";
        description = "no description";
        isAvailable  = true;
        maxOccupancy = DEFAULT_MAX_OCCUPANCY;
        basePrice = DEFAULT_BASE_PRICE;
    }

    public Amenity(String name, String description, float basePrice){
        this.name = name;
        this.description = description;
        isAvailable = true;
        maxOccupancy = DEFAULT_MAX_OCCUPANCY;
        if(basePrice>0){
            this.basePrice = basePrice;
        }else{
            basePrice = DEFAULT_BASE_PRICE;
        }
        currentPrice = basePrice;
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

    public double getCurrentPrice() {
        return currentPrice;
    }

    public int getMaxOccupancy(){
        return maxOccupancy;
    }
    public ArrayList<Guest> getOccupants(){
        return occupants;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public void setMaxOccupancy(int maxOccupancy){
        this.maxOccupancy = maxOccupancy;
    }

    //display name, description, and availability of amenity
    public void displayAmenityDetails(){
        System.out.printf("%s\nDescription: %s\nAvailability: %s\n", name, description,
            isAvailable ? "available" : "unavailable");
    }

    //reserve method overridden from Reservable, returns true if reservation is successful, false if not
    @Override
    public boolean reserve(Guest g){
        if(isAvailable) {
            occupants.add(g);
            g.getAmenitiesBooked().add(this);
            if(occupants.size() == maxOccupancy){
                isAvailable = false;
            }
            totalReservations++;
            updatePrice();
            return true;
        }else{
            System.out.println("Amenity is unavailable");
            return false;
        }
    }

    //cancel method overridden from Reservable
    @Override
    public boolean cancel(Guest g) {
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

    //guest with reservation rates amenity from 0-10
    public void rate(Guest g, int rating){
        if(occupants.contains(g)){
            if(rating >= 0 && rating <= 10){
                ratings.add(rating);
                System.out.println("Successfully added rating to " + name + ".");
                updatePrice();
            }
        }
    }

    //return average rating
    public float calculateRating(){
        float avg = 0;
        for(int rating : ratings){
            avg += rating;
        }
        return avg/ratings.size();
    }

    //update price based on total reservations and current rating
    public void updatePrice(){
        float addedDemandPrice = totalReservations*0.1f;
        float ratingMultiplier = 1+((calculateRating()-5)/10);
        currentPrice = (currentPrice+addedDemandPrice)*ratingMultiplier;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return name.equals(((Amenity) obj).getName());
    }

}