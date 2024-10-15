import java.util.ArrayList;

public class Buffet extends Amenity implements Reservable{
    private ArrayList<String> menu = new ArrayList<>();
    private ArrayList<ArrayList<Guest>> parties = new ArrayList<>();
    private enum MenuType{BREAKFAST, LUNCH, DINNER}

    MenuType menuType;
    
    public Buffet(){
        super();
    };

    public Buffet(String name, String description, double price, MenuType menuType){
        super(name, description, price);
        this.menuType = menuType;
    }

        

    public void showMenu(){
        System.out.println(menu.toString());
    }

    public boolean reserveParty(ArrayList<Guest> party){
        if(occupants.size() + party.size() <= maxOccupancy){
            for(Guest g : party){
                occupants.add(g);
            }
            if(occupants.size() == maxOccupancy)
                isAvailable = false;
            return true;
        }else{
            System.out.println("Currently no space for party of size " + party.size());
            return false;
        }
    }
}