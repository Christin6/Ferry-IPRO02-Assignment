import java.time.LocalDateTime;
import java.time.Month;

public class App {
    static FerryManagement ferryManagement = new FerryManagement();

    public static void main(String[] args) {
        Ferry f1 = new Ferry("F1", 100);
        Ferry f2 = new Ferry("F2", 100);
        Ferry f3 = new Ferry("F3", 100);

        FerryTrip t1 = new FerryTrip("Sydney", "Jakarta", 80, LocalDateTime.of(2026, Month.MAY, 15, 10, 30));
        FerryTrip t2 = new FerryTrip("Jakarta", "Sydney", 70, LocalDateTime.of(2026, Month.MAY, 20, 12, 30));
        FerryTrip t3 = new FerryTrip("Kuala Lumpur", "Tokyo", 120, LocalDateTime.of(2026, Month.DECEMBER, 13, 9, 30));
        FerryTrip t4 = new FerryTrip("Tokyo", "Kuala Lumpur", 130, LocalDateTime.of(2026, Month.DECEMBER, 16, 9, 30));
        FerryTrip t5 = new FerryTrip("London", "Paris", 180, LocalDateTime.of(2026, Month.NOVEMBER, 28, 16, 30));
        FerryTrip t6 = new FerryTrip("Paris", "London", 190, LocalDateTime.of(2026, Month.DECEMBER, 2, 14, 30));

        ferryManagement.addFerryTrip(f1, t1);
        ferryManagement.addFerryTrip(f1, t2);
        ferryManagement.addFerryTrip(f2, t3);
        ferryManagement.addFerryTrip(f2, t4);
        ferryManagement.addFerryTrip(f3, t5);
        ferryManagement.addFerryTrip(f3, t6);

        App app = new App();
        app.runMainMenu();
    }

    void runMainMenu() {
        while (true) {
            System.out.println("--- Main Menu ---");
            System.out.println("Are you a customer or admin?");
            System.out.println("1. Customer");
            System.out.println("2. Admin");
            System.out.println("0. Exit");

            int choice = In.nextInt();

            if (choice == 1) {
                customerMenu();
            } else if (choice == 2) {
                adminMenu();
            } else if (choice == 0) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    void customerMenu() {
        while (true) {
            System.out.println("---Customer Menu---");
            System.out.println("What would you like to do?");
            System.out.println("0. Back to main menu");
            System.out.println("1. Book a trip");
            System.out.println("2. Check current booking");
            System.out.println("3. Check ferry availability for a trip");

            int choice = In.nextInt();

            if (choice == 0) {
                System.out.println("Returning to main menu...");
                break;
            } else if (choice == 1) {

            } else if (choice == 2) {

            } else if (choice == 3) {
                System.out.println("Date of the trip?");

            } else if (choice == 4) {

            } else {
                System.out.println("Please choose 0, 1, 2, 3, or 4,");
            }
        }
    }

    void adminMenu() {
        while (true) {
            System.out.println("--- Admin Menu ---");
            System.out.println("0. Back to main menu");
            System.out.println("1. Create a ferry trip");
            System.out.println("2. View bookings of a ferry");
            System.out.println("3. Assign discount to a ferry trip");

            int choice = In.nextInt();
            if (choice == 0) {
                break;
            } else if (choice == 1) {
                System.out.println(ferryManagement);
            } else if (choice == 2) {
                
            } else if (choice == 3) {

            } else if (choice == 4) {

            } else {
                System.out.println("Please choose 0, 1, 2, 3, or 4,");
            }
        }
    }
}