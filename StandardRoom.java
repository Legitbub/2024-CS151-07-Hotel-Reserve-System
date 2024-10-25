public class StandardRoom extends Room {
    public StandardRoom() {
        level = "Standard";
        price = 100;

    }

    public StandardRoom(int roomID) {
        level = "Standard";
        this.roomID = roomID;
        price = 100;
    }

    public StandardRoom(Guest guest, int roomID, double price) {
        level = "Standard";
        this.guest = guest;
        this.roomID = roomID;
        this.price = price;
    }
}
