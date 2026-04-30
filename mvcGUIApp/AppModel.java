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

    void printAllTrips() {
        System.out.println("List of all the trips:");

        int count = 1;

        for (FerryTrip trip : this.trips) {
            if (trip == null) {
                System.out.println(count + ". (No trip scheduled)");
                count++;
                continue;
            }
            System.out.println(count + ". " + "Trip from "
                    + trip.startingPointProperty().get() + " to " +
                    trip.destinationProperty().get() + " (Price: $" + trip.getPrice() + ")");

            count++;
        }
    }

    Customer findCustomerByName(String name) {
        for (FerryTrip trip : this.trips) {
            if (trip == null)
                continue;
            for (Customer cust : trip.getCustomers()) {
                if (cust.getName().equals(name)) {
                    return cust;
                }
            }
        }
        return null; // new customer
    }

    public ArrayList<FerryTrip> customerBookedTrip(String customerName) {
        ArrayList<FerryTrip> bookingList = new ArrayList<>();

            for (FerryTrip trip : this.trips) {
                if (trip == null) {
                    continue;
                }
                for (Customer cust : trip.getCustomers()) {
                    if (cust.getName().equals(customerName)) {
                        bookingList.add(trip);
                    }
                }
            }

        return bookingList;
    }

    boolean setGuardian(String parentName, ChildCustomer child) {
            for (FerryTrip trip : this.trips) {
                if (trip == null) {
                    continue;
                }
                for (Customer cust : trip.getCustomers()) {
                    if (cust.getName().equals(parentName)) {
                        child.setGuardian((AdultCustomer) cust);
                        return true;
                    }
                }
            }
        return false;
    }

    private boolean seatAvailability(FerryTrip trip, Ferry ferry) {
        if (trip.getCustomers().size() < ferry.getMaxSeats()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean seatAvailable(FerryTrip trip) {
        for (Ferry ferry : trips.keySet()) {
            ArrayList<FerryTrip> tripList = trips.get(ferry);
            if (tripList.contains(trip)) {
                return seatAvailability(trip, ferry);
            }
        }
        return false;
    }

    ArrayList<FerryTrip> getAvailability(String destination, String startingPoint) {
        ArrayList<FerryTrip> availableTrips = new ArrayList<>();

        for (FerryTrip trip : this.trips) {
                if (trip == null) {
                    continue;
                }
                if (trip.destinationProperty().get().equals(destination)
                        && trip.startingPointProperty().get().equals(startingPoint)) {
                    availableTrips.add(trip);
                }
            }

        return availableTrips;
    }

    ArrayList<FerryTrip> getAvailability(String destination, String startingPoint,
            double priceMaximum) {
        ArrayList<FerryTrip> availableTrips = new ArrayList<>();

            for (FerryTrip trip : this.trips) {
                if (trip == null) {
                    continue;
                }
                if (trip.destinationProperty().get().equals(destination)
                        && trip.startingPointProperty().get().equals(startingPoint)
                        && trip.getPrice() <= priceMaximum) {
                    availableTrips.add(trip);
                }
            }

        return availableTrips;
    }

    // Get all current bookings and their revenue
    void getFerryTripsData() {
        System.out.println("Here are all the ferry trips:\n");

        int count = 1;

            System.out.println("Ferry: " + this.ferries + "\n");

            for (FerryTrip f : this.trips) {
                if (f == null) {
                    System.out.println(count + ". (No trip scheduled)");
                } else {
                    System.out.println(count + ". " + f);
                    System.out.println("Total revenue: $" + f.getCurrentRevenue() + "\n");
                }
                count++;
            }

            System.out.println("-----------------------------\n");

        System.out.println("");
    }

    public static final Comparator<FerryTrip> compareByPriceAsc = Comparator.comparing(FerryTrip::getCurrentRevenue);
    public static final Comparator<FerryTrip> compareByPriceDesc = Comparator.comparing(FerryTrip::getCurrentRevenue)
            .reversed();

    void getFerryTripsDataSorted(Comparator<FerryTrip> comparator) {
        ArrayList<FerryTrip> sortedTrips = new ArrayList<>();

        for (Map.Entry<Ferry, ArrayList<FerryTrip>> trip : trips.entrySet()) {
            ArrayList<FerryTrip> ferryTrip = trip.getValue();

            for (FerryTrip f : ferryTrip) {
                if (f != null) {
                    sortedTrips.add(f);
                }
            }
        }

        Collections.sort(sortedTrips, comparator);

        System.out.println("Here are all the ferry trips:\n");
        if (sortedTrips.isEmpty()) {
            System.out.println("There are currently no active trips.");
        }

        int count = 1;
        for (FerryTrip f : sortedTrips) {
            System.out.println(count + ". " + f);
            System.out.println("Total revenue: $" + f.getCurrentRevenue() + "\n");
            count++;
        }

    }

    FerryTrip selectTripBasedOnIndex(int index) {
        int count = 1;

        for (Map.Entry<Ferry, ArrayList<FerryTrip>> trip : trips.entrySet()) {
            ArrayList<FerryTrip> ferryTrip = trip.getValue();

            for (FerryTrip f : ferryTrip) {
                if (count == index) {
                    return f;
                }
                count++;
            }

        }

        return null; // Return null if no trip is found at the specified index
    }

    @Override
    public void assignDiscount(double amount, FerryTrip tripTarget) {
        boolean executeSettingDiscount = true;

        // Making sure that the fixed price is not over the base price of the ticket
        for (Map.Entry<Ferry, ArrayList<FerryTrip>> trip : trips.entrySet()) {
            ArrayList<FerryTrip> ferryTrip = trip.getValue();

            for (FerryTrip f : ferryTrip) {
                // Making sure it is the targetted trip
                if (!(f == tripTarget)) {
                    continue;
                }
                if (amount > f.basePriceProperty().get()) {
                    System.out.println("Invalid amount, the discount cannot be over the base price!\n");
                    executeSettingDiscount = false;
                    break;
                }
            }
        }

        while (executeSettingDiscount) {
            for (Map.Entry<Ferry, ArrayList<FerryTrip>> trip : trips.entrySet()) {
                ArrayList<FerryTrip> ferryTrip = trip.getValue();

                for (FerryTrip f : ferryTrip) {
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

    @Override
    public void assignDiscount(int percentage, FerryTrip tripTarget) {
        if (percentage > 100) {
            System.out.println("Invalid percentage amount, the discount cannot be over than 100%!\n");
        } else {
            for (Map.Entry<Ferry, ArrayList<FerryTrip>> trip : trips.entrySet()) {
                ArrayList<FerryTrip> ferryTrip = trip.getValue();

                for (FerryTrip f : ferryTrip) {
                    if (f == tripTarget) {
                        double newAmount = (((double) percentage / 100) * (f.basePriceProperty().get()));

                        f.setDiscount(newAmount);
                    }
                }
            }

            System.out.println("Successfully applied discount!");
            System.out.println("The applied discount is " + percentage + "%\n");

        }
    };

    public String toString() {
        return trips.toString();
    }
}

interface AssignDiscount {
    void assignDiscount(double amount, FerryTrip tripTarget);

    void assignDiscount(int percentage, FerryTrip tripTarget);
}
