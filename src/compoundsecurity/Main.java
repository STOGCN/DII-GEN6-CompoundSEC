
import domain.card.AccessCard;
import domain.card.AccessCardFactory;
import domain.card.CardPermission;
import notification.floor.FloorLevel;
import notification.floor.Room;
import system.AccessControl;
import system.FloorAccessControl;
import system.RoomAccessControl;
import security.TimeBasedEncryption;
import domain.audit.AuditLogger;
import domain.audit.AuditLog;
import domain.audit.NotificationSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuditLogger auditLogger = AuditLogger.getInstance(); // Singleton
        NotificationSystem notificationSystem = new NotificationSystem();

        AccessControl floorControl = new FloorAccessControl();
        AccessControl roomControl = new RoomAccessControl();

        AccessCard card = null;

        while (true) {
            System.out.println("\n========= Access Control System =========");
            System.out.println("1. Create Access Card");
            System.out.println("2. Check Floor Access");
            System.out.println("3. Check Room Access");
            System.out.println("4. Modify Permissions");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // สร้างการ์ดใหม่
                    System.out.print("Enter card type (guest, employee, admin): ");
                    String cardType = scanner.nextLine().trim().toLowerCase();
                    System.out.print("Enter card ID: ");
                    String cardId = scanner.nextLine().trim();
                    
                    card = AccessCardFactory.createCard(cardType, cardId);
                    System.out.println("Created card: " + card.getCardId());
                    break;

                case "2":
                    // ตรวจสอบสิทธิ์เข้าชั้น
                    if (card == null) {
                        System.out.println("You need to create a card first!");
                        break;
                    }
                    System.out.print("Enter floor level (LOW, MEDIUM, HIGH): ");
                    String floorInput = scanner.nextLine().trim().toUpperCase();
                    try {
                        FloorLevel floorLevel = FloorLevel.valueOf(floorInput);
                        boolean access = floorControl.grantAccess(card, floorLevel);
                        System.out.println("Floor Access: " + access);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid floor level!");
                    }
                    break;

                case "3":
                    // ตรวจสอบสิทธิ์เข้าห้อง
                    if (card == null) {
                        System.out.println("You need to create a card first!");
                        break;
                    }
                    System.out.print("Enter room ID: ");
                    String roomId = scanner.nextLine().trim();
                    System.out.print("Enter floor level (LOW, MEDIUM, HIGH): ");
                    String roomFloorInput = scanner.nextLine().trim().toUpperCase();
                    try {
                        FloorLevel roomFloor = FloorLevel.valueOf(roomFloorInput);
                        Room room = new Room(roomId, roomFloor);
                        boolean access = roomControl.grantAccess(card, room);
                        System.out.println("Room Access: " + access);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid floor level!");
                    }
                    break;

                case "4":
                    // แก้ไขสิทธิ์
                    if (card == null) {
                        System.out.println("You need to create a card first!");
                        break;
                    }
                    System.out.print("Enter permission to add/remove (LOW_FLOOR_ACCESS, MEDIUM_FLOOR_ACCESS, HIGH_FLOOR_ACCESS, ROOM_ACCESS): ");
                    String permInput = scanner.nextLine().trim().toUpperCase();
                    try {
                        CardPermission permission = CardPermission.valueOf(permInput);
                        System.out.print("Do you want to (1) Add or (2) Remove permission? ");
                        String action = scanner.nextLine().trim();

                        if ("1".equals(action)) {
                            card.getPermissions().add(permission);
                            auditLogger.log(new AuditLog(card.getCardId(), "Added permission: " + permission));
                            notificationSystem.notify("Card " + card.getCardId() + " Added permission: " + permission);
                        } else if ("2".equals(action)) {
                            card.getPermissions().remove(permission);
                            auditLogger.log(new AuditLog(card.getCardId(), "Removed permission: " + permission));
                            notificationSystem.notify("Card " + card.getCardId() + " Removed permission: " + permission);
                        } else {
                            System.out.println("Invalid choice!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid permission!");
                    }
                    break;

                case "5":
                    // ออกจากโปรแกรม
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
