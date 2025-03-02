package domain.card;

public class AccessCardFactory {
    public static AccessCard createCard(String type, String cardId) {
        AccessCard.Builder builder = new AccessCard.Builder(cardId);

        switch (type.toLowerCase()) {
            case "guest":
                builder.addPermission(CardPermission.LOW_FLOOR_ACCESS);
                break;
            case "employee":
                builder.addPermission(CardPermission.LOW_FLOOR_ACCESS)
                       .addPermission(CardPermission.MEDIUM_FLOOR_ACCESS);
                break;
            case "admin":
                builder.addPermission(CardPermission.LOW_FLOOR_ACCESS)
                       .addPermission(CardPermission.MEDIUM_FLOOR_ACCESS)
                       .addPermission(CardPermission.HIGH_FLOOR_ACCESS)
                       .addPermission(CardPermission.ROOM_ACCESS);
                break;
            default:
                throw new IllegalArgumentException("Invalid card type: " + type);
        }
        return builder.build();
    }
}

