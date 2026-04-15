import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class FerryManagement {
    private HashMap<Ferry, ArrayList<FerryTrip>> trips;

    FerryManagement() {
        trips = new HashMap<>();
    }

    void addFerryTrip(Ferry ferry, FerryTrip trip) {
        trips.put(ferry, new ArrayList<>());
        trips.get(ferry).add(trip);
    }

    void bookTrip(Customer customer, FerryTrip trip) {
        trip.addCustomer(customer);
    }

    void checkBooking(Customer customer) {
        ArrayList<FerryTrip> bookingList = new ArrayList<>();

        for (ArrayList<FerryTrip> tripList : trips.values()) {
            for (FerryTrip trip : tripList) {
                for (Customer cust : trip.getCustomers()) {
                    if (cust.getName().equals(customer.getName())) {
                        bookingList.add(trip);
                    }
                }
            }
        }

        if (bookingList.isEmpty()) {
            System.out.println("No booking made.");
        } else {
            for (FerryTrip t : bookingList) {
                System.out.println(t);
            }
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