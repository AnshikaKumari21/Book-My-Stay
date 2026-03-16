import java.util.HashMap;

// Room class
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

// RoomInventory class (stores availability)
class RoomInventory {

    HashMap<String, Integer> availability = new HashMap<>();
    HashMap<String, Room> rooms = new HashMap<>();

    // Register room type
    public void registerRoom(Room room, int count) {
        rooms.put(room.type, room);
        availability.put(room.type, count);
    }
}

// Search Service (read-only)
class SearchService {

    private RoomInventory inventory;

    SearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // Display available rooms only
    public void searchAvailableRooms() {

        System.out.println("Room Search\n");

        for (String type : inventory.rooms.keySet()) {

            int available = inventory.availability.get(type);

            // Filter unavailable rooms
            if (available > 0) {

                Room r = inventory.rooms.get(type);

                System.out.println(type + " Room:");
                System.out.println("Beds: " + r.beds);
                System.out.println("Size: " + r.size + " sqft");
                System.out.println("Price per night: " + r.price);
                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }
}

// Main class
public class BookMyStay {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Register rooms
        inventory.registerRoom(new Room("Single", 1, 250, 1500.0), 5);
        inventory.registerRoom(new Room("Double", 2, 400, 2500.0), 3);
        inventory.registerRoom(new Room("Suite", 3, 750, 5000.0), 2);

        // Guest searches for rooms
        SearchService search = new SearchService(inventory);
        search.searchAvailableRooms();
    }
}