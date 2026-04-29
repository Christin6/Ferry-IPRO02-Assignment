import java.util.ArrayList;

public class FerryTrip {
  private String destination, startingPoint;
  private double basePrice;
  private double discount = 0;
  private ArrayList<Customer> customers;
  private Ferry assignedFerry;

  FerryTrip(String destination, String startingPoint, double basePrice, Ferry ferry) {
    this.destination = destination;
    this.startingPoint = startingPoint;
    this.basePrice = basePrice;
    this.customers = new ArrayList<>();
    this.assignedFerry = ferry;
  }

  Ferry getAssignedFerry() {
    return this.assignedFerry;
  }

  String getDestination() {
    return this.destination;
  }

  String getStartingPoint() {
    return this.startingPoint;
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

  void addCustomer(Customer customer) {
    this.customers.add(customer);
  }

  void setDiscount(double newValue) {
    this.discount = newValue;
  }

  double getPrice() {
    return basePrice - discount;
  }

  double getBasePrice() {
    return this.basePrice;
  }

  ArrayList<Customer> getCustomers() {
    return this.customers;
  }

  public String toString() {
    return "FerryTrip\n" +
        "destination: " + destination + '\n' +
        "startingPoint: " + startingPoint + '\n' +
        "basePrice: " + basePrice + '\n' +
        "discount: " + discount + '\n' +
        "customers: " + customers + '\n';
  }
}
