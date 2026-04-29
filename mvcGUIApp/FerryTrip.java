import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class FerryTrip {
  private final SimpleStringProperty destination;
  private final SimpleStringProperty startingPoint;
  private final SimpleDoubleProperty basePrice;
  private double discount = 0;
  private ArrayList<Customer> customers;
  private Ferry assignedFerry;

  FerryTrip(String destination, String startingPoint, double basePrice, Ferry ferry) {
    this.destination = new SimpleStringProperty(destination);
    this.startingPoint = new SimpleStringProperty(startingPoint);
    this.basePrice = new SimpleDoubleProperty(basePrice);
    this.customers = new ArrayList<>();
    this.assignedFerry = ferry;
  }

  Ferry getAssignedFerry() {
    return this.assignedFerry;
  }

  public SimpleStringProperty destinationProperty(){
    return this.destination;
  }

  public SimpleStringProperty startingPointProperty(){
    return this.startingPoint;
  }

  public SimpleDoubleProperty basePriceProperty(){
    return this.basePrice;
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
    return this.basePrice.get() - discount;
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
