public class VIPRoom extends Room implements Reservable {
    public VIPRoom() {
        level = "VIP";
    }

    public VIPRoom(int roomID) {
        level = "VIP";
        this.roomID = roomID;
        price = 150;
    }

    public VIPRoom(Guest guest, int roomID, double price) {
        level = "VIP";
        this.guest = guest;
        this.roomID = roomID;
        this.price = price;
    }
}
