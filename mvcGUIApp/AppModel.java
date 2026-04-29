import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class AppModel {
    private final ObservableList<FerryTrip> trips;

    AppModel() {
        // dummy data
        Ferry f1 = new Ferry("F1", 100);
        Ferry f2 = new Ferry("F2", 100);
        Ferry f3 = new Ferry("F3", 100);
        Ferry f4 = new Ferry("F4", 100);

        this.trips = FXCollections.observableArrayList(
            new FerryTrip("Sydney", "Jakarta", 80, f1),
            new FerryTrip("Jakarta", "Sydney", 70, f1),
            new FerryTrip("Kuala Lumpur", "Tokyo", 120, f2),
            new FerryTrip("Tokyo", "Kuala Lumpur", 130, f3),
            new FerryTrip("London", "Paris", 180, f4),
            new FerryTrip("Paris", "London", 190, f4)
        );
    }
}
