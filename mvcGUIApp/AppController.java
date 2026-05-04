public class AppController {
    private final AppModel model;

    public AppController(AppModel model) {
        this.model = model;
    };

    // Ferry Trips
    public void addTrip(FerryTrip trip) {
        this.model.addTrip(trip);
    }

    public void updateTrip(int index, String newDestination, String newStarting, double newBasePrice, Ferry newFerry) {
        this.model.updateTrip(index, newDestination, newStarting, newBasePrice, newFerry);
    }

    public void removeTrip(int index) {
        this.model.removeTrip(index);
    }

    public void removeTripInFerry(int FerryIndex) {
        this.model.removeTripInFerry(FerryIndex);
    }

    public void setFerryTripList(FerryTrip trip, int index) {
        this.model.setFerryTripList(trip, index);
    }

    // Ferries
    public void addFerry(Ferry ferry) {
        this.model.addFerry(ferry);
    }

    public void updateFerry(int index, String newName, int newMaxSeats) {
        this.model.updateFerry(index, newName, newMaxSeats);
    }

    public void removeFerry(int index) {
        this.model.removeFerry(index);
    }

    // Customer bookings
    public void createBooking(Customer customer, FerryTrip trip) {
        this.model.bookTrip(customer, trip);
    }

    public void setDiscount(double discount, FerryTrip trip){
        this.model.assignDiscount(discount, trip);
    }
}
