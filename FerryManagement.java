import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class FerryManagement {
    private HashMap<FerryTrip, ArrayList<Customer>> trips;

    FerryManagement() {
        trips = new HashMap<>();
    }

    void addFerryTrip(FerryTrip trip, ArrayList<Customer> customers) {
        trips.put(trip, customers);
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
