import java.util.ArrayList;
import java.util.List;

public class Buffet extends Amenity implements Reservable{
    private ArrayList<String> menu = new ArrayList<>();
    private ArrayList<ArrayList<Guest>> parties = new ArrayList<>();
    private String menuType;

    public Buffet(String name, String description, float basePrice, String menuType){
        super(name, description, basePrice);
        this.menuType = menuType;
    }

    public String getMenuType(){
        return menuType;
    }

    public void setMenuType(String menuType){
        this.menuType = menuType;
    }

    public ArrayList<ArrayList<Guest>> getParties(){
        return parties;
    }        

    public void showMenu(){
        System.out.println(menu.toString());
    }

    @Override
    public boolean reserve(Guest g){
        if(super.reserve(g)){
            parties.add(new ArrayList<>(List.of(g)));
            return true;
        }else{
            return false;
        }
    }

    public boolean reserveParty(ArrayList<Guest> party){
        if(occupants.size() + party.size() <= maxOccupancy){
            for(Guest g : party){
                occupants.add(g);
                totalReservations++;
            }
            parties.add(party);
            if(occupants.size() == maxOccupancy)
                isAvailable = false;
            return true;
        }else{
            System.out.println("Currently no space for party of size " + party.size());
            return false;
        }
    }
}