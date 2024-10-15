public abstract class Room implements Reservable, Comparable<Room> {
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

    @Override
    public boolean cancel(Guest g) {
        System.out.println(g.getName() + " checked out of room " + roomID);
        return true;
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

    @Override
    public int compareTo(Room o) {
        if (o == null) {
            return 0;
        } else if (roomID < o.getRoomID()) {
            return -1;
        } else if (roomID > o.getRoomID()) {
            return 1;
        } else {
            return 0;
        }
    }
}
