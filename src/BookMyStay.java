// Abstract Room class
abstract class Room {
    int beds;
    int size;
    double price;
    int available;

    // Constructor
    Room(int beds, int size, double price, int available) {
        this.beds = beds;
        this.size = size;
        this.price = price;
        this.available = available;
    }

    // Abstract method
    abstract void display();
}

// Single Room class
class SingleRoom extends Room {

    SingleRoom(int beds, int size, double price, int available) {
        super(beds, size, price, available);
    }

    void display() {
        System.out.println("Single Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + available);
        System.out.println();
    }
}

// Double Room class
class DoubleRoom extends Room {

    DoubleRoom(int beds, int size, double price, int available) {
        super(beds, size, price, available);
    }

    void display() {
        System.out.println("Double Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + available);
        System.out.println();
    }
}

// Suite Room class
class SuiteRoom extends Room {

    SuiteRoom(int beds, int size, double price, int available) {
        super(beds, size, price, available);
    }

    void display() {
        System.out.println("Suite Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + available);
    }
}

// Main class
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("Hotel Room Initialization\n");

        // Creating room objects
        SingleRoom single = new SingleRoom(1, 250, 1500.0, 5);
        DoubleRoom doubleRoom = new DoubleRoom(2, 400, 2500.0, 3);
        SuiteRoom suite = new SuiteRoom(3, 750, 5000.0, 2);

        // Display room details
        single.display();
        doubleRoom.display();
        suite.display();
    }
}