public class AppController {
    private final AppModel model;
    private final FerryManagement ferryManagementModel;

    public AppController(AppModel model, FerryManagement ferryManagementModel) {
        this.model = model;
        this.ferryManagementModel = ferryManagementModel;
    }

    public void addTrip(FerryTrip trip){
        this.model.addTrip(trip);
    }

    public void addFerry(Ferry ferry){
        this.model.addFerry(ferry);
    }

    public void 
}
