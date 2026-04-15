import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class FerryManagement {
    private HashMap<Ferry, ArrayList<FerryTrip>> trips;

    FerryManagement() {
        trips = new HashMap<>();
    }

    void addFerryTrip(Ferry ferry, FerryTrip trip) {
        trips.put(ferry, new ArrayList<>());
        trips.get(ferry).add(trip);
    }

    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String detination, String startingPoint) {
        return new ArrayList<>();
    }

    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String detination, String startingPoint, double priceMaximum) {
        return new ArrayList<>();
    }

    public String toString() {
        return trips.toString();
    }
}

interface AssignDiscount {
    void assignDiscount(double amount, FerryTrip tripTarget);
    
    void assignDiscount(int percentage, FerryTrip tripTarget);
}