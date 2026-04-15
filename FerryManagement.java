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

    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String destination, String startingPoint) {
      return new ArrayList<>();
    }

    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String destination, String startingPoint, double priceMaximum) {
      return new ArrayList<>();
<<<<<<< HEAD
=======
    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String detination, String startingPoint) {
        return new ArrayList<>();
    }

    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String detination, String startingPoint, double priceMaximum) {
        return new ArrayList<>();
>>>>>>> dfe2ab3c554e165fc43b94dd8ef882de8b12933f
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
>>>>>>> dfe2ab3c554e165fc43b94dd8ef882de8b12933f
}