package security;

public class TimeBasedEncryption implements Encryption {
    @Override
    public String encrypt(String data) {
        // Simulate time-based encryption
        return "ENCRYPTED_" + data + "_" + System.currentTimeMillis();
    }
}