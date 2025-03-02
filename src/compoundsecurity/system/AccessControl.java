package system;

import domain.card.AccessCard;
import notification.floor.FloorLevel;
import notification.floor.Room;

public interface AccessControl {
    boolean grantAccess(AccessCard card, FloorLevel floorLevel);
    boolean grantAccess(AccessCard card, Room room);
}

