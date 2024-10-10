public abstract class Room implements Reservable {
    protected int roomID;
    protected String level;
    protected Guest guest;
    protected double price;
    protected boolean isReserved = false;

    @Override
    public void reserve(Guest g) {
        isReserved = true;
        guest = g;
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
