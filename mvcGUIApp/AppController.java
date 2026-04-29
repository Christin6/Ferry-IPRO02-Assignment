public class AppController {
    private final AppModel model;
    private final FerryManagement ferryManagementModel;

    public AppController(AppModel model, FerryManagement ferryManagementModel) {
        this.model = model;
<<<<<<< HEAD
        this.ferryManagementModel = ferryManagementModel;
    }

    public void addTrip(FerryTrip trip){
        this.model.addTrip(trip);
    }

    public void addFerry(Ferry ferry){
        this.model.addFerry(ferry);
    }

    public void 
=======
    };

    //Ferry Trips
    public void addTrip(FerryTrip trip) {
        this.model.addTrip(trip);
    }
    
    public void updateTrip(int index, FerryTrip trip) {
        this.model.updateTrip(index, trip);
    }

    public void removeTrip(int index) {
        this.model.removeTrip(index);
    }

    //Ferries
    public void addFerry(Ferry ferry) {
        this.model.addFerry(ferry);
    }

    public void updateFerry(int index, Ferry ferry) {
        this.model.updateFerry(index, ferry);
    }

    public void removeFerry(int index) {
        this.model.removeFerry(index);
    }
>>>>>>> 0572ae1909d56e4592e620d1a921ad933e021aa4
}
