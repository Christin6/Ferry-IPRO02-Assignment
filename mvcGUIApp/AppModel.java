public class AppModel {
    private final FerryManagement ferryManagement;

    AppModel() {
        this.ferryManagement = new FerryManagement();
        // dummy data
        Ferry f1 = new Ferry("F1", 100);
        Ferry f2 = new Ferry("F2", 100);
        Ferry f3 = new Ferry("F3", 100);
        Ferry f4 = new Ferry("F4", 100);

        FerryTrip t1 = new FerryTrip("Sydney", "Jakarta", 80);
        FerryTrip t2 = new FerryTrip("Jakarta", "Sydney", 70);
        FerryTrip t3 = new FerryTrip("Kuala Lumpur", "Tokyo", 120);
        FerryTrip t4 = new FerryTrip("Tokyo", "Kuala Lumpur", 130);
        FerryTrip t5 = new FerryTrip("London", "Paris", 180);
        FerryTrip t6 = new FerryTrip("Paris", "London", 190);

        this.ferryManagement.addFerryTrip(f1, t1);
        this.ferryManagement.addFerryTrip(f1, t2);
        this.ferryManagement.addFerryTrip(f2, t3);
        this.ferryManagement.addFerryTrip(f2, t4);
        this.ferryManagement.addFerryTrip(f3, t5);
        this.ferryManagement.addFerryTrip(f3, t6);
        this.ferryManagement.addEmptyFerry(f4);
    }
}
