package domain.card;

import java.util.HashSet;
import java.util.Set;

public class AccessCard {
    private String cardId ;
    private Set<CardPermission> permissions;
    private String encryptedData;

    private AccessCard(Builder builder) {
        this.cardId = builder.cardId;
        this.permissions = builder.permissions;
        this.encryptedData = builder.encryptedData;
    }

    public String getCardId() {
        return cardId;
    }

    public Set<CardPermission> getPermissions() {
        return permissions;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public static class Builder {
        private String cardId;
        private Set<CardPermission> permissions = new HashSet<>();
        private String encryptedData;

        public Builder(String cardId) {
            this.cardId = cardId;
        }

        public Builder addPermission(CardPermission permission) {
            this.permissions.add(permission);
            return this;
        }

        public Builder setEncryptedData(String encryptedData) {
            this.encryptedData = encryptedData;
            return this;
        }

        public AccessCard build() {
            return new AccessCard(this);
        }
    }
}

