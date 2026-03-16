import java.util.LinkedList;
import java.util.Queue;

// Reservation class (represents a booking request)
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Booking Request Queue
class BookingQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    // Add booking request
    public void addRequest(Reservation r) {
        queue.add(r);
    }

    // Process requests in arrival order
    public void processRequests() {

        System.out.println("Booking Request Queue");

        while (!queue.isEmpty()) {
            Reservation r = queue.poll();
            System.out.println("Processing booking for Guest: "
                    + r.guestName + ", Room Type: " + r.roomType);
        }
    }
}

// Main class
public class BookMyStay{

    public static void main(String[] args) {

        BookingQueue bookingQueue = new BookingQueue();

        // Guests submit booking requests
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Double"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Process queue
        bookingQueue.processRequests();
    }
}