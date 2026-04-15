import java.util.HashMap;
import java.util.Map;
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
      ArrayList<FerryTrip> availableTrips = new ArrayList<>();

      for (Ferry ferry : trips.keySet()) {
        for (FerryTrip trip : trips.get(ferry)) {
          if (trip.getTripDateTime().equals(dateTime) && trip.getDestination().equals(destination) && trip.getStartingPoint().equals(startingPoint)) {
            availableTrips.add(trip);
          }
        }
      }

      return availableTrips;
    }

    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String destination, String startingPoint, double priceMaximum) {
      return new ArrayList<>();
    }

    void getFerryTrips() {
      System.out.println("Here are all the ferry trips:\n");

      for (Map.Entry<Ferry, ArrayList<FerryTrip>> trip : trips.entrySet()) {
        ArrayList<FerryTrip> ferryTrip = trip.getValue();
        for (FerryTrip f : ferryTrip) {
          System.out.println(f);
          System.out.println("Total revenue: $" + f.getCurrentRevenue() + "\n");
        }
      }

      System.out.println("");
    }

    public String toString() {
        return trips.toString();
    }
}

interface AssignDiscount {
  void assignDiscount(double amount, FerryTrip tripTarget);

  void assignDiscount(int percentage, FerryTrip tripTarget);
}