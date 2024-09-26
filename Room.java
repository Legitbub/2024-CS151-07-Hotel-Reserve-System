public abstract class Room implements Reservable {
    protected int roomID;
    protected String level;
    protected Guest guest;
    protected boolean isReserved = false;

    @Override
    public void reserve(Guest g) {
        isReserved = true;
        guest = g;
    }

    public int getRoomID() {
        return roomID;
    }
}
