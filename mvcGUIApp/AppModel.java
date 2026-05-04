import javafx.collections.ObservableList;
import java.util.ArrayList;

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

    public void updateTrip(int index, String newDestination, String newStarting, double newBasePrice, Ferry newFerry) {
        FerryTrip existingTrip = this.trips.get(index);
        existingTrip.setDestination(newDestination);
        existingTrip.setStartingPoint(newStarting);
        existingTrip.setBasePrice(newBasePrice);
        existingTrip.setFerry(newFerry);
    }

    public void removeTrip(int index) {
        this.trips.remove(index);
    }

    public void removeTripInFerry(int ferryIndex) {
        ArrayList<FerryTrip> toDeleteTrips = searchTripsInFerry(ferryIndex);
        for (FerryTrip trip : toDeleteTrips) {
            for (int i = 0; i < this.trips.size(); i++) {
                if (this.trips.get(i).equals(trip)) {
                    removeTrip(i);
                }
            }
        }
    }

    // Ferries
    public void addFerry(Ferry ferry) {
        this.ferries.add(ferry);
    }

    public void updateFerry(int index, String newName, int newSeats) {
        Ferry existingFerry = this.ferries.get(index);
        existingFerry.setName(newName);
        existingFerry.setMaxSeats(newSeats);
    }

    public void removeFerry(int index) {
        this.ferries.remove(index);
    }

    void setFerryTripList(FerryTrip trip, int index) {
        this.trips.set(index, trip);
    }

    public void bookTrip(Customer customer, FerryTrip trip) {
        for (FerryTrip t : this.trips) {
            if (t.equals(trip)) {
                trip.addCustomer(customer);
            }
        }
    }

    public AdultCustomer searchGuardian(String customerName) {
        for (FerryTrip trip : this.trips) {
            if (trip == null) {
                continue;
            }
            for (Customer cust : trip.customersProperty()) {
                if (cust.getName().equals(customerName)) {
                    AdultCustomer adultCustomer = (AdultCustomer) cust;
                    return adultCustomer;
                }

                else {
                    return null;
                }
            }
        }
        return null;
    }

    public ArrayList<FerryTrip> searchTripsInFerry(int ferryIndex) {
        ArrayList<FerryTrip> trips = new ArrayList<>();

        Ferry searchedFerry = ferriesProperty().get(ferryIndex);

        for (FerryTrip trip : this.trips) {
            if (trip.assignedFerryProperty().get().equals(searchedFerry)) {
                trips.add(trip);
            }
        }

        return trips;
    }

    public ArrayList<FerryTrip> customerBookedTrip(String customerName) {
        ArrayList<FerryTrip> bookingList = new ArrayList<>();

        for (FerryTrip trip : this.trips) {
            if (trip == null) {
                continue;
            }
            for (Customer cust : trip.customersProperty()) {
                if (cust.getName().equals(customerName)) {
                    bookingList.add(trip);
                }
            }
        }
        return bookingList;
    }

    public void assignDiscount(double amount, FerryTrip tripTarget) {
        boolean executeSettingDiscount = true;

        // Making sure that the fixed price is not over the base price of the ticket
            for (FerryTrip f : this.trips) {
                // Making sure it is the targetted trip
                if (!(f == tripTarget)) {
                    continue;
                }
                if (amount > f.priceProperty().get()) {
                    System.out.println("Invalid amount, the discount cannot be over the base price!\n");
                    executeSettingDiscount = false;
                    break;
                }
            }

        if (executeSettingDiscount) {
                for (FerryTrip f : this.trips) {
                    if (f == tripTarget) {
                        f.setDiscount(amount);
                    }
                }
            }

            System.out.println("Successfully applied discount!");
            System.out.println("The applied discount is $" + amount + "\n");
            executeSettingDiscount = false;
        }
    }
