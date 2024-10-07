public interface Reservable {
    //Set item as reserved to a specific guest
    void reserve(Guest g);

    //remove guest's reservation
    void cancel(Guest g);
}
