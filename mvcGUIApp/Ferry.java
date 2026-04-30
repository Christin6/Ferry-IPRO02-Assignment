import javafx.beans.property.SimpleStringProperty;

public class Ferry {
  private SimpleStringProperty name;
  private int maxSeats;

  Ferry(String name, int maxSeats) {
    this.name = new SimpleStringProperty(name);
    this.maxSeats = maxSeats;
  }

  public int getMaxSeats() {
      return maxSeats;
  }

  SimpleStringProperty nameProperty() {
    return this.name;
  }

  public String toString() {
    return name + " | maxSeats: " + maxSeats;
  }
}
