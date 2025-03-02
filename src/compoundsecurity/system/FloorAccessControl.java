package system;

import domain.card.AccessCard;
import domain.card.CardPermission;
import notification.floor.FloorLevel;
import notification.floor.Room;

public class FloorAccessControl implements AccessControl {
    @Override
    public boolean grantAccess(AccessCard card, FloorLevel floorLevel) {
        return card.getPermissions().contains(floorLevelToPermission(floorLevel));
    }

    private CardPermission floorLevelToPermission(FloorLevel floorLevel) {
        switch (floorLevel) {
            case LOW: return CardPermission.LOW_FLOOR_ACCESS;
            case MEDIUM: return CardPermission.MEDIUM_FLOOR_ACCESS;
            case HIGH: return CardPermission.HIGH_FLOOR_ACCESS;
            default: throw new IllegalArgumentException("Invalid floor level");
        }
    }

    @Override
    public boolean grantAccess(AccessCard card, Room room) {
        return grantAccess(card, room.getFloorLevel());
    }
}

