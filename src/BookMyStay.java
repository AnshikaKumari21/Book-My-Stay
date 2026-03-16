import java.util.HashMap;

// Room class to store room details
class Room {
    String type;
    int beds;
    int size;
    double price;

    Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }
}

// RoomInventory class for centralized management
class RoomInventory {

    private HashMap<String, Integer> availability = new HashMap<>();
    private HashMap<String, Room> rooms = new HashMap<>();

    // Register a room type
    public void registerRoom(Room room, int count) {
        rooms.put(room.type, room);
        availability.put(room.type, count);
    }

    // Update room availability
    public void updateAvailability(String type, int newCount) {
        availability.put(type, newCount);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("Hotel Room Inventory Status\n");

        for (String type : rooms.keySet()) {
            Room r = rooms.get(type);

            System.out.println(type + " Room:");
            System.out.println("Beds: " + r.beds);
            System.out.println("Size: " + r.size + " sqft");
            System.out.println("Price per night: " + r.price);
            System.out.println("Available Rooms: " + availability.get(type));
            System.out.println();
        }
    }
}

// Main class
public class BookMyStay {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Register room types
        inventory.registerRoom(new Room("Single", 1, 250, 1500.0), 5);
        inventory.registerRoom(new Room("Double", 2, 400, 2500.0), 3);
        inventory.registerRoom(new Room("Suite", 3, 750, 5000.0), 2);

        // Display inventory
        inventory.displayInventory();
    }
}