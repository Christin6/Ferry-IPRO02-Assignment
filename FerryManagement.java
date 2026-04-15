import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Map;

public class FerryManagement implements AssignDiscount {
    private HashMap<Ferry, ArrayList<FerryTrip>> trips;
    private HashMap<Customer, ArrayList<FerryTrip>> customerBookings;

    FerryManagement() {
        trips = new HashMap<>();
        customerBookings = new HashMap<>();
    }

    void addFerryTrip(Ferry ferry, FerryTrip trip) {
        if (!trips.containsKey(ferry)) {
            trips.put(ferry, new ArrayList<>());
        }
        trips.get(ferry).add(trip);
    }

    void bookTrip(Customer customer, FerryTrip trip) {
        for (ArrayList<FerryTrip> tripList : trips.values()) {
            if (tripList.contains(trip)) {
                customerBookings.put(customer, new ArrayList<>());
                customerBookings.get(customer).add(trip);
            }
        }
    }

    void checkBooking(Customer customer, FerryTrip trip) {
        ArrayList<FerryTrip> bookingList = customerBookings.get(customer);
        if (!bookingList.contains(trip)){
            System.out.println("No booking made.");
        } else {
        for (FerryTrip t : bookingList) {
            System.out.println(t);
        }
    }
    }

    ArrayList<FerryTrip> getAvailability(LocalDate date, String destination, String startingPoint) {
        ArrayList<FerryTrip> availableTrips = new ArrayList<>();

        for (Ferry ferry : trips.keySet()) {
            for (FerryTrip trip : trips.get(ferry)) {
                if (trip.getTripDateTime().toLocalDate().equals(date) && trip.getDestination().equals(destination)
                        && trip.getStartingPoint().equals(startingPoint)) {
                    availableTrips.add(trip);
                }
            }
        }

        return availableTrips;
    }

    ArrayList<FerryTrip> getAvailability(LocalDate date, String destination, String startingPoint,
            double priceMaximum) {
        ArrayList<FerryTrip> availableTrips = new ArrayList<>();

        for (Ferry ferry : trips.keySet()) {
            for (FerryTrip trip : trips.get(ferry)) {
                if (trip.getTripDateTime().toLocalDate().equals(date) && trip.getDestination().equals(destination)
                        && trip.getStartingPoint().equals(startingPoint) && trip.getPrice() <= priceMaximum) {
                    availableTrips.add(trip);
                }
            }
        }

        return availableTrips;
    }

    // Get all current bookings and their revenue
    void getFerryTripsData() {
        System.out.println("Here are all the ferry trips:\n");

        int count = 1;

        for (Map.Entry<Ferry, ArrayList<FerryTrip>> trip : trips.entrySet()) {
            ArrayList<FerryTrip> ferryTrip = trip.getValue();

            for (FerryTrip f : ferryTrip) {
                System.out.println(count + ". " + f);
                System.out.println("Total revenue: $" + f.getCurrentRevenue() + "\n");
                count++;
            }

        }

        System.out.println("");
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

    public void assignDiscount(double amount, FerryTrip tripTarget) {
        for (Map.Entry<Ferry, ArrayList<FerryTrip>> trip : trips.entrySet()) {
            ArrayList<FerryTrip> ferryTrip = trip.getValue();

            for (FerryTrip f : ferryTrip) {
                if (f == tripTarget) {
                    f.setDiscount(amount);
                }
            }

        }
    };

    public void assignDiscount(int percentage, FerryTrip tripTarget) {
        for (Map.Entry<Ferry, ArrayList<FerryTrip>> trip : trips.entrySet()) {
            ArrayList<FerryTrip> ferryTrip = trip.getValue();

            for (FerryTrip f : ferryTrip) {
                if (f == tripTarget) {
                    f.setDiscount(percentage);
                }
            }

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