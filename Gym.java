import java.util.HashMap;

public class Gym extends Amenity implements Reservable{
    private HashMap<Guest,Employee> personalTrainingSessions = new HashMap<>();

    public Gym(String name, String description, float basePrice){
        super(name, description, basePrice);
    }

    public HashMap<Guest,Employee> getPersonalTrainingSessions(){
        return personalTrainingSessions;
    }

    @Override
    public boolean reserve(Guest g){
        if(super.reserve(g)){
            System.out.println("If you would like, you can now request a personal trainer!");
            return true;
        }
        return false;
    }

    public boolean reservePersonalTrainer(Guest g, Employee e){
        if(!occupants.contains(g)){
            System.out.println("Please make a gym reservation before reserving a personal trainer");
            return false;
        }else if (personalTrainingSessions.containsKey(g)) {
            System.out.println("Guest " + g.getName() + " already has a personal trainer reserved.");
            return false;
        }else if (personalTrainingSessions.containsValue(e)){
            System.out.println("Employee + " + e.getName() + " is already assigned to another guest");
            return false;
        }else{
            personalTrainingSessions.put(g, e);
            System.out.println("Successfully reserved perssonal trainer.");
            return true;
        }
    }
}