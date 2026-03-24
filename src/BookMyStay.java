import java.util.*;

// Reservation class (confirmed booking)
class Reservation {
    String guestName;
    String roomId;

    Reservation(String guestName, String roomId) {
        this.guestName = guestName;
        this.roomId = roomId;
    }
}

// Booking History (stores confirmed reservations)
class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    // Add confirmed booking
    public void addReservation(Reservation r) {
        history.add(r);
    }

    // Get all reservations (read-only)
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(history); // return copy (safe)
    }
}

// Booking Report Service
class BookingReportService {

    private BookingHistory history;

    BookingReportService(BookingHistory history) {
        this.history = history;
    }

    // Display all bookings
    public void displayAllBookings() {
        System.out.println("Booking History Report\n");

        List<Reservation> list = history.getAllReservations();

        for (Reservation r : list) {
            System.out.println("Guest: " + r.guestName + ", Room ID: " + r.roomId);
        }
    }

    // Summary report
    public void displaySummary() {
        List<Reservation> list = history.getAllReservations();

        System.out.println("\nSummary Report");
        System.out.println("Total Bookings: " + list.size());

        // Count bookings per room type
        Map<String, Integer> countMap = new HashMap<>();

        for (Reservation r : list) {
            String type = r.roomId.split("-")[0];
            countMap.put(type, countMap.getOrDefault(type, 0) + 1);
        }

        for (String type : countMap.keySet()) {
            System.out.println(type + " Rooms Booked: " + countMap.get(type));
        }
    }
}

// Main class
public class BookMyStay {

    public static void main(String[] args) {

        // Initialize booking history
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings (from previous use case)
        history.addReservation(new Reservation("Abhi", "Single-1"));
        history.addReservation(new Reservation("Subha", "Single-2"));
        history.addReservation(new Reservation("Vanmathi", "Suite-1"));

        // Admin requests report
        BookingReportService report = new BookingReportService(history);

        report.displayAllBookings();
        report.displaySummary();
    }
}