package notification;

public class ConsoleNotifier implements Observer {
    @Override
    public void update(String message) {
        System.out.println("[Notification] " + message);
    }
}
