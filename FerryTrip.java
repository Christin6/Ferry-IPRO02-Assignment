import java.time.LocalDateTime;
import java.util.ArrayList;

public class FerryTrip {
  private String destination, startingPoint;
  private double basePrice;
  private double discount = 0;
  private ArrayList <Customer> customers;
  private LocalDateTime tripDateTime;

  FerryTrip(String destination, String startingPoint, double basePrice, LocalDateTime tripDateTime) {
    this.destination = destination;
    this.startingPoint = startingPoint;
    this.basePrice = basePrice;
    this.tripDateTime = tripDateTime;
    this.customers = new ArrayList<>();
  }

  String getDestination() {
    return this.destination;
  }

  String getStartingPoint() {
    return this.startingPoint;
  }

  LocalDateTime getTripDateTime() {
    return this.tripDateTime;
  }

  double getCurrentRevenue() {
    double totalRevenue = 0;

    for (Customer customer : customers) {
      if (customer instanceof AdultCustomer) {
        totalRevenue += getPrice();
      }
      else {
        ChildCustomer child = (ChildCustomer) customer;
        totalRevenue += getPrice()*child.getChildFareMultiplier();
      }
    }

    return totalRevenue;
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
            "basePrice: " + basePrice + '\n' +
            "discount: " + discount + '\n' +
            "customers: " + customers + '\n' +
            "tripDateTime: " + tripDateTime + '\n';
  }
}
