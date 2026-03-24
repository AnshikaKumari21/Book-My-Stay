import java.util.*;

// Reservation class
class Reservation {
    String guestName;
    String roomId;
    boolean isCancelled;

    Reservation(String guestName, String roomId) {
        this.guestName = guestName;
        this.roomId = roomId;
        this.isCancelled = false;
    }
}

// Booking History
class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public Reservation findReservation(String roomId) {
        for (Reservation r : history) {
            if (r.roomId.equals(roomId)) {
                return r;
            }
        }
        return null;
    }

    public void displayHistory() {
        System.out.println("\nBooking History:");
        for (Reservation r : history) {
            String status = r.isCancelled ? "CANCELLED" : "CONFIRMED";
            System.out.println("Guest: " + r.guestName +
                    ", Room ID: " + r.roomId +
                    ", Status: " + status);
        }
    }
}

// Inventory Service
class InventoryService {

    private Map<String, Integer> availability = new HashMap<>();

    public void addRoomType(String type, int count) {
        availability.put(type, count);
    }

    public void increment(String type) {
        availability.put(type, availability.getOrDefault(type, 0) + 1);
    }

    public void displayInventory() {
        System.out.println("\nUpdated Inventory:");
        for (String type : availability.keySet()) {
            System.out.println(type + " Rooms Available: " + availability.get(type));
        }
    }
}

// Cancellation Service
class CancellationService {

    private BookingHistory history;
    private InventoryService inventory;
    private Stack<String> rollbackStack = new Stack<>();

    CancellationService(BookingHistory history, InventoryService inventory) {
        this.history = history;
        this.inventory = inventory;
    }

    public void cancelBooking(String roomId) {

        System.out.println("Processing Cancellation for Room ID: " + roomId);

        // Validate reservation
        Reservation r = history.findReservation(roomId);

        if (r == null) {
            System.out.println("Cancellation failed: Reservation not found.");
            return;
        }

        if (r.isCancelled) {
            System.out.println("Cancellation failed: Already cancelled.");
            return;
        }

        // Push to rollback stack
        rollbackStack.push(roomId);

        // Extract room type
        String type = roomId.split("-")[0];

        // Restore inventory
        inventory.increment(type);

        // Mark cancelled
        r.isCancelled = true;

        System.out.println("Cancellation successful for Guest: " + r.guestName);
    }

    public void showRollbackStack() {
        System.out.println("\nRollback Stack (LIFO): " + rollbackStack);
    }
}

// Main Class
public class HotelBookingApp {

    public static void main(String[] args) {

        // Initialize components
        BookingHistory history = new BookingHistory();
        InventoryService inventory = new InventoryService();

        // Setup inventory (after some bookings already happened)
        inventory.addRoomType("Single", 3);
        inventory.addRoomType("Suite", 1);

        // Existing confirmed bookings
        history.addReservation(new Reservation("Abhi", "Single-1"));
        history.addReservation(new Reservation("Subha", "Single-2"));
        history.addReservation(new Reservation("Vanmathi", "Suite-1"));

        // Cancellation service
        CancellationService cancelService = new CancellationService(history, inventory);

        // Guest cancels booking
        cancelService.cancelBooking("Single-2");

        // Display results
        history.displayHistory();
        inventory.displayInventory();
        cancelService.showRollbackStack();
    }
}