public interface Reservable {
    //Set item as reserved to a specific guest
    boolean reserve(Guest g);

    //remove guest's reservation
    boolean cancel(Guest g);
}
