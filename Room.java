public abstract class Room implements Reservable {
    protected int roomID;
    protected String level;
    protected Guest guest;
    protected double price;
    protected boolean isReserved = false;

    @Override
    public boolean reserve(Guest g) {
        if(!isReserved){
            guest = g;
            isReserved = true;
            System.out.println("Room " + roomID +
                    " successfully reserved for " + g.getName());
            return true;
        }else{
            System.out.println("Room " + roomID + " already reserved");
            return false;
        }
    }

    public int getRoomID() {
        return roomID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    
}
