import java.util.HashMap;

public class Buffet extends Amenity implements Reservable{
    private HashMap<String, Double> menu = new HashMap<>();
    


    public void order(Guest g, String item){
        if(!occupants.contains(g)){
            System.out.println("Please make a reservation before ordering");
        }else if (menu.containsKey(item)){
            System.out.printf("Successfully ordered %s, $%d added to guest %s's balance.", item, menu.get(item), g.getName());
            g.
        }else{
            System.out.println()
        }
    }
}