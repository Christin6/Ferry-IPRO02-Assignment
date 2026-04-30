public class AppController {
    private final AppModel model;

    public AppController(AppModel model) {
        this.model = model;
    };

    // Ferry Trips
    public void addTrip(FerryTrip trip) {
        this.model.addTrip(trip);
    }

    public void updateTrip(int index, FerryTrip trip) {
        this.model.updateTrip(index, trip);
    }

    public void removeTrip(int index) {
        this.model.removeTrip(index);
    }

    // Ferries
    public void addFerry(Ferry ferry) {
        this.model.addFerry(ferry);
    }

    public void updateFerry(int index, Ferry ferry) {
        this.model.updateFerry(index, ferry);
    }

    public void removeFerry(int index) {
        this.model.removeFerry(index);
    }

    // Customer bookings
    public void addBooking() {
        
    }
}
