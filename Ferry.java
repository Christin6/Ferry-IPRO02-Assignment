import java.util.ArrayList;

public class Ferry {
  protected String name;
  protected ArrayList<FerryTrip> trips;
  Ferry(String name) {
    this.name = name;
    trips = new ArrayList<>();
  }

  void addTrip(FerryTrip trip) {
    trips.add(trip);
  }

  public String toString() {
    return "Ferry\n" +
            "name: " + name + '\n' +
            "trips: " + trips + '\n';
  }
}
