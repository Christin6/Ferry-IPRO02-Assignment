import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class FerryManagement {
    private HashMap<Ferry, ArrayList<FerryTrip>> trips;
    private HashMap<Customer, ArrayList<FerryTrip>> customerBookings;

    FerryManagement() {
        trips = new HashMap<>();
        customerBookings = new HashMap<>();
    }

    void addFerryTrip(Ferry ferry, FerryTrip trip) {
        trips.put(ferry, new ArrayList<>());
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

    void checkBooking(Customer customer) {
        ArrayList<FerryTrip> bookingList = customerBookings.get(customer);
        for (FerryTrip trip : bookingList) {
            System.out.println(trip);
        }
    }

    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String destination, String startingPoint) {
        return new ArrayList<>();
    }

    ArrayList<FerryTrip> getAvailability(LocalDateTime dateTime, String destination, String startingPoint,
            double priceMaximum) {
        return new ArrayList<>();
    }

    public String toString() {
        return trips.toString();
    }
}

interface AssignDiscount {
    void assignDiscount(double amount, FerryTrip tripTarget);

    void assignDiscount(int percentage, FerryTrip tripTarget);
}