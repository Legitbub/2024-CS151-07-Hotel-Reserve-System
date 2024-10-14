public class StandardRoom extends Room implements Reservable {
    public StandardRoom() {
        level = "Standard";
        price = 0;
        
    }

    public StandardRoom(int roomID) {
        level = "Standard";
        this.roomID = roomID;
        this.price = price;
    }

    public StandardRoom(Guest guest, int roomID, double price) {
        level = "Standard";
        this.guest = guest;
        this.roomID = roomID;
        this.price = price;
    }
}
