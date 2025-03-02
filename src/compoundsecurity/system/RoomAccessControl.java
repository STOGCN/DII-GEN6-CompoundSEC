package system;

import domain.card.AccessCard;
import domain.card.CardPermission;
import notification.floor.FloorLevel;
import notification.floor.Room;

public class RoomAccessControl implements AccessControl {
    @Override
    public boolean grantAccess(AccessCard card, FloorLevel floorLevel) {
        return false; // Room access control does not handle floor-level access
    }

    @Override
    public boolean grantAccess(AccessCard card, Room room) {
        return card.getPermissions().contains(CardPermission.ROOM_ACCESS);
    }
}

