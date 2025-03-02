package domain.audit;

import java.time.LocalDateTime;

public class AuditLog {
    private String cardId;
    private String action;
    private LocalDateTime timestamp;

    public AuditLog(String cardId, String action) {
        this.cardId = cardId;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "cardId='" + cardId + '\'' +
                ", action='" + action + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

