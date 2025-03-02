package notification.floor;

public class Room {
    private String roomId;
    private FloorLevel floorLevel;

    public Room(String roomId, FloorLevel floorLevel) {
        this.roomId = roomId;
        this.floorLevel = floorLevel;
    }

    public String getRoomId() {
        return roomId;
    }

    public FloorLevel getFloorLevel() {
        return floorLevel;
    }
}

