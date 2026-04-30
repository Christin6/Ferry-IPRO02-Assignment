import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import javafx.collections.FXCollections;

public class AppModel {
    private final ObservableList<FerryTrip> trips;
    private final ObservableList<Ferry> ferries;

    AppModel() {
        // dummy data
        this.ferries = FXCollections.observableArrayList(
                new Ferry("F1", 100),
                new Ferry("F2", 100),
                new Ferry("F3", 100),
                new Ferry("F4", 100));

        this.trips = FXCollections.observableArrayList(
                new FerryTrip("Sydney", "Jakarta", 80, this.ferries.get(0)),
                new FerryTrip("Jakarta", "Sydney", 70, this.ferries.get(1)),
                new FerryTrip("Kuala Lumpur", "Tokyo", 120, this.ferries.get(2)),
                new FerryTrip("Tokyo", "Kuala Lumpur", 130, this.ferries.get(2)),
                new FerryTrip("London", "Paris", 180, this.ferries.get(3)),
                new FerryTrip("Paris", "London", 190, this.ferries.get(3)));
    }

    public ObservableList<FerryTrip> tripsProperty() {
        return this.trips;
    }

    public ObservableList<Ferry> ferriesProperty() {
        return this.ferries;
    }

    // Ferry Trips
    public void addTrip(FerryTrip trip) {
        this.trips.add(trip);
    }

    public void updateTrip(int index, FerryTrip trip) {
        this.trips.set(index, trip);
    }

    public void removeTrip(int index) {
        this.trips.remove(index);
    }

    // Ferries
    public void addFerry(Ferry ferry) {
        this.ferries.add(ferry);
    }

    public void updateFerry(int index, Ferry ferry) {
        this.ferries.set(index, ferry);
    }

    public void removeFerry(int index) {
        this.ferries.remove(index);
    }

    void setFerryTripList(FerryTrip trip, int index) {
        this.trips.set(index, trip);
    }

    void bookTrip(Customer customer, FerryTrip trip) {
        trip.addCustomer(customer);
    }
}