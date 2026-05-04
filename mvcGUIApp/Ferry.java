import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ferry {
  private SimpleStringProperty name;
  private SimpleIntegerProperty maxSeats;

  Ferry(String name, int maxSeats) {
    this.name = new SimpleStringProperty(name);
    this.maxSeats = new SimpleIntegerProperty(maxSeats);
  }

  SimpleIntegerProperty maxSeatsProperty() {
      return maxSeats;
  }

  SimpleStringProperty nameProperty() {
    return this.name;
  }

  public void setName(String newName) {
    this.name.set(newName);
  }

  public void setMaxSeats(int newMaxSeats) {
    this.maxSeats.set(newMaxSeats);
  }
}
