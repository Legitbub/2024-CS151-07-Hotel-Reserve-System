public class VIPRoom extends Room implements Reservable {
    public VIPRoom() {
        level = "VIP";
    }

    public VIPRoom(int guestID, int roomID) {
        level = "VIP";
        this.guestID = guestID;
        this.roomID = roomID;
    }
    @Override
    public void reserve() {

    }

    public void callRoomService() {

    }
}
