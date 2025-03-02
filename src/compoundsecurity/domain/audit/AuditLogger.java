package domain.audit;

public class AuditLogger {
    private static AuditLogger instance = new AuditLogger();

    private AuditLogger() {}

    public static AuditLogger getInstance() {
        return instance;
    }

    public void log(AuditLog log) {
        System.out.println(log);
    }
}

