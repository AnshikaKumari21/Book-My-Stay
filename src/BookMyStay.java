import java.util.*;

// Add-On Service class
class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map<ReservationID, List of Services>
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to a reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Get services for a reservation
    public List<AddOnService> getServices(String reservationId) {
        return serviceMap.getOrDefault(reservationId, new ArrayList<>());
    }

    // Calculate total additional cost
    public double calculateTotalCost(String reservationId) {
        double total = 0;
        for (AddOnService s : getServices(reservationId)) {
            total += s.getCost();
        }
        return total;
    }

    // Display services
    public void displayServices(String reservationId) {
        System.out.println("Add-On Services for Reservation ID: " + reservationId);

        List<AddOnService> services = getServices(reservationId);

        if (services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (AddOnService s : services) {
            System.out.println("- " + s.getServiceName() + " : " + s.getCost());
        }

        System.out.println("Total Additional Cost: " + calculateTotalCost(reservationId));
    }
}

// Main class
public class BookMyStay {

    public static void main(String[] args) {

        // Example reservation ID (from previous booking system)
        String reservationId = "Single-1";

        // Initialize service manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Guest selects services
        manager.addService(reservationId, new AddOnService("Breakfast", 300.0));
        manager.addService(reservationId, new AddOnService("WiFi", 100.0));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 800.0));

        // Display selected services and total cost
        manager.displayServices(reservationId);
    }
}