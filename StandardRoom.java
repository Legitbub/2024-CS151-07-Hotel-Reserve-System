public class StandardRoom extends Room implements Reservable {
    public StandardRoom() {
        level = "Standard";
    }

    public StandardRoom(int roomID) {
        level = "Standard";
        this.roomID = roomID;
    }

    public StandardRoom(Guest guest, int roomID) {
        level = "Standard";
        this.guest = guest;
        this.roomID = roomID;
    }
}
