import java.time.LocalDateTime;
import java.util.ArrayList;

enum TripType {
  ONE_WAY_TRIP, TWO_WAY_TRIP
}

public class FerryTrip {
  private String destination, startingPoint;
  private TripType tripType;
  private double basePrice;
  private double discount = 0;
  private ArrayList <Customer> customers;
  private LocalDateTime tripDateTime;

  FerryTrip(String destination, String startingPoint, TripType tripType, double basePrice, LocalDateTime tripDateTime) {
    this.destination = destination;
    this.startingPoint = startingPoint;
    this.tripType = tripType;
    this.basePrice = basePrice;
    this.tripDateTime = tripDateTime;
    this.customers = new ArrayList<>();
  }

  double getCurrentRevenue() {
    return 0;
  }

  void setDiscount(double newValue) {
    this.discount = newValue;
  }

  double getPrice() {
    return basePrice * (1 - discount);
  }

  public String toString() {
    return "FerryTrip\n" +
            "destination: " + destination + '\n' +
            "startingPoint: " + startingPoint + '\n' +
            "tripType: " + tripType + '\n' +
            "basePrice: " + basePrice + '\n' +
            "discount: " + discount + '\n' +
            "customers: " + customers + '\n' +
            "tripDateTime: " + tripDateTime + '\n';
  }
}
