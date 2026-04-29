public class AppModel {
    private final ObservableList<Ferry> ferries;

    AppModel() {
        this.ferryManagement = new FerryManagement();
        // dummy data
        Ferry f1 = new Ferry("F1", 100);
        Ferry f2 = new Ferry("F2", 100);
        Ferry f3 = new Ferry("F3", 100);
        Ferry f4 = new Ferry("F4", 100);

        FerryTrip t1 = new FerryTrip("Sydney", "Jakarta", 80, f1);
        FerryTrip t2 = new FerryTrip("Jakarta", "Sydney", 70, f1);
        FerryTrip t3 = new FerryTrip("Kuala Lumpur", "Tokyo", 120, f2);
        FerryTrip t4 = new FerryTrip("Tokyo", "Kuala Lumpur", 130, f3);
        FerryTrip t5 = new FerryTrip("London", "Paris", 180, f4);
        FerryTrip t6 = new FerryTrip("Paris", "London", 190, f4);


    }
}
