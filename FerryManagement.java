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

import java.util.ArrayList;

interface AssignDiscount {
  void assignDiscount(double amount, FerryTrip tripTarget);

  void assignDiscount(int percentage, FerryTrip tripTarget);
}

public class Admin {
  private String name;
  private ArrayList<Ferry> managedFerries;
  private ArrayList<FerryTrip> managedTrips;

  Admin(String name) {
    this.name = name;
    this.managedTrips = new ArrayList<>();
    this.managedFerries = new ArrayList<>();
  }

  Ferry f1 = new Ferry("F1", 100);
  Ferry f2 = new Ferry("F2", 100);
  Ferry f3 = new Ferry("F3", 100);

  void addFerry() {
    this.managedFerries.add(f1);
    this.managedFerries.add(f2);
    this.managedFerries.add(f3);
  }

  void assignFerryTrip(FerryTrip trip, Ferry ferry) {

  }

  void getAllTrips() {

  }

  public String toString(){
    return 
  }
}
