import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation class
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Room Inventory
class RoomInventory {

    private Map<String, Integer> availability = new HashMap<>();

    public void addRoomType(String type, int count) {
        availability.put(type, count);
    }

    public boolean isValidRoomType(String type) {
        return availability.containsKey(type);
    }

    public int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }
}

// Validator Class
class ReservationValidator {

    public void validate(Reservation r, RoomInventory inventory) throws InvalidBookingException {

        // Validate guest name
        if (r.guestName == null || r.guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Validate room type
        if (!inventory.isValidRoomType(r.roomType)) {
            throw new InvalidBookingException("Invalid room type: " + r.roomType);
        }

        // Validate availability
        if (inventory.getAvailability(r.roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for " + r.roomType);
        }
    }
}

// Booking Queue
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.add(r);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Reservation getNext() {
        return queue.poll();
    }
}

// Main Class
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        // Initialize components
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Add room types
        inventory.addRoomType("Single", 2);
        inventory.addRoomType("Double", 1);

        try {
            // Take user input
            System.out.print("Enter Guest Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Room Type (Single/Double/Suite): ");
            String type = scanner.nextLine();

            Reservation r = new Reservation(name, type);

            // Validate booking
            validator.validate(r, inventory);

            // Add to queue if valid
            bookingQueue.addRequest(r);

            System.out.println("Booking request added successfully.");

        } catch (InvalidBookingException e) {
            // Handle validation errors
            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }

        System.out.println("System continues running safely.");
    }
}