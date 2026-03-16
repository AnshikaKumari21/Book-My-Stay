import java.util.*;

// Reservation class
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Inventory Service
class InventoryService {

    private HashMap<String, Integer> availability = new HashMap<>();
    private HashMap<String, Integer> roomCounter = new HashMap<>();
    private HashSet<String> allocatedRooms = new HashSet<>();

    // Initialize inventory
    public void addRoomType(String type, int count) {
        availability.put(type, count);
        roomCounter.put(type, 0);
    }

    // Allocate room safely
    public String allocateRoom(String type) {

        int available = availability.getOrDefault(type, 0);

        if (available <= 0) {
            return null;
        }

        int id = roomCounter.get(type) + 1;
        String roomId = type + "-" + id;

        // Ensure unique room ID
        if (allocatedRooms.contains(roomId)) {
            return null;
        }

        allocatedRooms.add(roomId);
        roomCounter.put(type, id);
        availability.put(type, available - 1);

        return roomId;
    }
}

// Booking Service
class BookingService {

    private Queue<Reservation> queue;
    private InventoryService inventory;

    BookingService(Queue<Reservation> queue, InventoryService inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void processBookings() {

        System.out.println("Room Allocation Processing");

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();

            String roomId = inventory.allocateRoom(r.roomType);

            if (roomId != null) {
                System.out.println("Booking confirmed for Guest: "
                        + r.guestName + ", Room ID: " + roomId);
            } else {
                System.out.println("No rooms available for " + r.roomType);
            }
        }
    }
}

// Main class
public class HotelBookingApp {

    public static void main(String[] args) {

        // Create booking queue
        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Abhi", "Single"));
        bookingQueue.add(new Reservation("Subha", "Single"));
        bookingQueue.add(new Reservation("Vanmathi", "Suite"));

        // Initialize inventory
        InventoryService inventory = new InventoryService();
        inventory.addRoomType("Single", 5);
        inventory.addRoomType("Double", 3);
        inventory.addRoomType("Suite", 2);

        // Process bookings
        BookingService bookingService = new BookingService(bookingQueue, inventory);
        bookingService.processBookings();
    }
}