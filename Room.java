public abstract class Room implements Reservable {
    protected int roomID;
    protected String level;
    protected int guestID;

    @Override
    public abstract void reserve();
}
