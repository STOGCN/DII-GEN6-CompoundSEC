package domain.card;

import domain.audit.AuditLogger;
import domain.audit.AuditLog;
import notification.NotificationService;

public class CardManager {
    private AuditLogger auditLogger;
    private NotificationService notificationService;

    public CardManager(AuditLogger auditLogger, NotificationService notificationService) {
        this.auditLogger = auditLogger;
        this.notificationService = notificationService;
    }

    public void addPermission(AccessCard card, CardPermission permission) {
        card.getPermissions().add(permission);
        String message = "Added permission: " + permission;
        auditLogger.log(new AuditLog(card.getCardId(), message));
        notificationService.notifyObservers("Card " + card.getCardId() + " " + message);
    }

    public void revokePermission(AccessCard card, CardPermission permission) {
        card.getPermissions().remove(permission);
        String message = "Revoked permission: " + permission;
        auditLogger.log(new AuditLog(card.getCardId(), message));
        notificationService.notifyObservers("Card " + card.getCardId() + " " + message);
    }
}
