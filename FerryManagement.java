import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FerryManagement {
    private HashMap<Ferry, ArrayList<FerryTrip>> trips;

    FerryManagement() {
        trips = new HashMap<>();
    }

    void addFerryTrip(Ferry ferry, FerryTrip trip) {
        if (!trips.containsKey(ferry)) {
            trips.put(ferry, new ArrayList<>());
        }
        trips.get(ferry).add(trip);
    }

    ArrayList<FerryTrip> getAvailability(LocalDate date, String destination, String startingPoint) {
      ArrayList<FerryTrip> availableTrips = new ArrayList<>();

      for (Ferry ferry : trips.keySet()) {
        for (FerryTrip trip : trips.get(ferry)) {
          if (trip.getTripDateTime().toLocalDate().equals(date) && trip.getDestination().equals(destination) && trip.getStartingPoint().equals(startingPoint)) {
            availableTrips.add(trip);
          }
        }
      }

      return availableTrips;
    }

    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String destination, String startingPoint, double priceMaximum) {
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