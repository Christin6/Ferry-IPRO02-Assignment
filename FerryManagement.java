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

<<<<<<< HEAD
    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String destination, String startingPoint) {
      return new ArrayList<>();
    }

    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String destination, String startingPoint, double priceMaximum) {
      return new ArrayList<>();
=======
    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String detination, String startingPoint) {
        return new ArrayList<>();
    }

    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String detination, String startingPoint, double priceMaximum) {
        return new ArrayList<>();
>>>>>>> 86847f4fa74f94f468a816fd1aefc3bd32cda1cf
    }

    public String toString() {
        return trips.toString();
    }
}

interface AssignDiscount {
<<<<<<< HEAD
  void assignDiscount(double amount, FerryTrip tripTarget);

  void assignDiscount(int percentage, FerryTrip tripTarget);
=======
    void assignDiscount(double amount, FerryTrip tripTarget);
    
    void assignDiscount(int percentage, FerryTrip tripTarget);
>>>>>>> 86847f4fa74f94f468a816fd1aefc3bd32cda1cf
}