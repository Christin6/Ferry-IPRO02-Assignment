import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

public class FerryTrip {
  private final SimpleStringProperty destination;
  private final SimpleStringProperty startingPoint;
  private SimpleDoubleProperty basePrice;
  private SimpleDoubleProperty finalPrice;
  private SimpleDoubleProperty discount = new SimpleDoubleProperty(0);
  private ObservableList<Customer> customers;
  private SimpleObjectProperty<Ferry> assignedFerry;

  FerryTrip(String destination, String startingPoint, double basePrice, Ferry ferry) {
    this.destination = new SimpleStringProperty(destination);
    this.startingPoint = new SimpleStringProperty(startingPoint);
    this.basePrice = new SimpleDoubleProperty(basePrice);
    this.finalPrice = new SimpleDoubleProperty(basePrice);
    this.customers = FXCollections.observableArrayList();
    this.assignedFerry = new SimpleObjectProperty<>(ferry);

    // to make sure final price changes when discount or base price is changed
    this.basePrice.addListener((obs, oldVal, newVal) -> {
        this.finalPrice.set(newVal.doubleValue() - this.discount.get());
    });
    this.discount.addListener((obs, oldVal, newVal) -> {
        this.finalPrice.set(this.basePrice.get() - newVal.doubleValue());
    });
  }

  public void setDestination(String destination) {
    this.destination.set(destination);
  }

  public void setStartingPoint(String startingPoint) {
    this.startingPoint.set(startingPoint);
  }

  public void setBasePrice(double basePrice) {
    this.basePrice.set(basePrice);
  }

  public void setFerry(Ferry newFerry) {
    this.assignedFerry.set(newFerry);
  }

  public SimpleObjectProperty<Ferry> assignedFerryProperty() {
    return this.assignedFerry;
  }

  public ObservableList<Customer> customersProperty() {
    return this.customers;
  }

  public SimpleStringProperty destinationProperty() {
    return this.destination;
  }

  public SimpleStringProperty startingPointProperty() {
    return this.startingPoint;
  }

  public SimpleDoubleProperty discountProperty() {
    return this.discount;
  }

  public SimpleDoubleProperty basePriceProperty() {
    return this.basePrice;
  }

  public SimpleDoubleProperty priceProperty() {
    return this.finalPrice;
  }

  SimpleDoubleProperty getCurrentRevenue() {
    SimpleDoubleProperty totalRevenue = new SimpleDoubleProperty(0);

    for (Customer customer : customers) {
      if (customer instanceof AdultCustomer) {
        totalRevenue.add(priceProperty());
      } else {
        ChildCustomer child = (ChildCustomer) customer;
        totalRevenue.add(priceProperty().multiply(child.getChildFareMultiplier()));
      }
    }

    return totalRevenue;
  }

  void addCustomer(Customer customer) {
    this.customers.add(customer);
  }

  void setDiscount(double newValue) {
    this.discount.set(newValue);
  }

  public String toString() {
    return "FerryTrip\n" +
        "destination: " + destinationProperty().get() + '\n' +
        "startingPoint: " + startingPointProperty().get() + '\n' +
        "customers: " + customers + '\n';
  }
}
