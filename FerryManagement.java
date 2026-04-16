import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Map;

public class FerryManagement implements AssignDiscount {
    private HashMap<Ferry, ArrayList<FerryTrip>> trips;

    FerryManagement() {
        trips = new HashMap<>();
    }

    void addFerryTrip(Ferry ferry, FerryTrip trip) {
        if (!trips.containsKey(ferry)) {
            trips.put(ferry, new ArrayList<>());
        }
        trips.get(ferry).add(trip);
    }

    void setFerryTripList(FerryTrip trip, int index) {
        int count = 1;

        for (Map.Entry<Ferry, ArrayList<FerryTrip>> ferry : trips.entrySet()) {
            ArrayList<FerryTrip> ferryTrip = ferry.getValue();

            for (int i = 0; i < ferryTrip.size(); i++) {
                if (count == index) {
                    ferryTrip.set(i, trip);
                    return;
                }
                count++;
            }
        }
    }

    void bookTrip(Customer customer, FerryTrip trip) {
        trip.addCustomer(customer);
    }

    void printAllTripsWithoutCustomer() {
        System.out.println("List of all the trips:");

        int count = 1;

        for (ArrayList<FerryTrip> tripList : trips.values()) {
            for (FerryTrip trip : tripList) {
                if (trip == null) {
                    System.out.println(count + ". (No trip scheduled)");
                    count++;
                    continue;
                }
                System.out.println(count + ". " + "Trip from "
                        + trip.getStartingPoint() + " to " +
                        trip.getDestination() + " on " + trip.getTripDateTime()
                        + " (Price: $" + trip.getPrice() + ")");

                count++;
            }
        }
    }

    Customer findCustomerByName(String name) {
        for (ArrayList<FerryTrip> tripList : trips.values()) {
            for (FerryTrip trip : tripList) {
                if (trip == null)
                    continue;
                for (Customer cust : trip.getCustomers()) {
                    if (cust.getName().equals(name)) {
                        return cust;
                    }
                }
            }
        }
        return null; // new customer
    }

    public ArrayList<FerryTrip> checkBookedTrip(String customerName) {
        ArrayList<FerryTrip> bookingList = new ArrayList<>();

        for (ArrayList<FerryTrip> tripList : trips.values()) {
            for (FerryTrip trip : tripList) {
                if (trip == null) {
                    continue;
                }
                for (Customer cust : trip.getCustomers()) {
                    if (cust.getName().equals(customerName)) {
                        bookingList.add(trip);
                    }
                }
            }
        }

        return bookingList;
    }

    boolean setGuardian(String parentName, ChildCustomer child) {
        for (ArrayList<FerryTrip> bookingList : trips.values()) {
            for (FerryTrip trip : bookingList) {
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

    ArrayList<FerryTrip> getAvailability(LocalDate date, String destination, String startingPoint) {
        ArrayList<FerryTrip> availableTrips = new ArrayList<>();

        for (Ferry ferry : trips.keySet()) {
            for (FerryTrip trip : trips.get(ferry)) {
                if (trip == null) {
                    continue;
                }
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
                if (trip == null) {
                    continue;
                }
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
            Ferry ferry = trip.getKey();
            System.out.println("Ferry: " + ferry + "\n");

            for (FerryTrip f : ferryTrip) {
                if (f == null) {
                    System.out.println(count + ". (No trip scheduled)");
                } else {
                    System.out.println(count + ". " + f);
                    System.out.println("Total revenue: $" + f.getCurrentRevenue() + "\n");
                }
                count++;
            }

            System.out.println("-----------------------------\n");

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

        System.out.println("Successfully applied discount!");
        System.out.println("The applied discount is $" + amount + "\n");
    };

    public void assignDiscount(int percentage, FerryTrip tripTarget) {
        for (Map.Entry<Ferry, ArrayList<FerryTrip>> trip : trips.entrySet()) {
            ArrayList<FerryTrip> ferryTrip = trip.getValue();

            for (FerryTrip f : ferryTrip) {
                if (f == tripTarget) {
                    double newAmount = (((double) percentage / 100) * (f.getBasePrice()));

                    f.setDiscount(newAmount);
                }
            }
        }

        System.out.println("Successfully applied discount!");
        System.out.println("The applied discount is " + percentage + "%\n");
    };

    public String toString() {
        return trips.toString();
    }
}

interface AssignDiscount {
    void assignDiscount(double amount, FerryTrip tripTarget);

    void assignDiscount(int percentage, FerryTrip tripTarget);
}