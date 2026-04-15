import java.util.ArrayList;

interface AssignDiscount {
  void assignDiscount(double amount, FerryTrip tripTarget);

  void assignDiscount(double percentage, FerryTrip tripTarget);
}

public class Admin {
  private String name;
  private ArrayList<FerryTrip> managedTrips;

  Admin(String name) {
    this.name = name;
    this.managedTrips = new ArrayList<>();
  }

  void assignFerryTrip(FerryTrip trip, Ferry ferry) {

  }

  void removeFerryTrip(FerryTrip trip, Ferry ferr) {

  }

  void getAllTrips() {
    
  }

  public String toString(){
    return 
  }
}
