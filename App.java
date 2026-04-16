import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class App {
    static FerryManagement ferryManagement = new FerryManagement();

    public static void main(String[] args) {
        Ferry f1 = new Ferry("F1", 100);
        Ferry f2 = new Ferry("F2", 100);
        Ferry f3 = new Ferry("F3", 100);
        Ferry f4 = new Ferry("F4", 100);

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
        ferryManagement.addFerryTrip(f4, null);
        ferryManagement.addFerryTrip(f4, null);

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
                bookTrip();
            } else if (choice == 2) {
                System.out.println("Enter your assigned name in the booking: ");
                String name = In.nextLine();

            } else if (choice == 3) {
                checkFerryAvailabilityMenu();
            } else {
                System.out.println("Please choose 0, 1, 2, or 3");
            }
        }
    }

    void adminMenu() {
        while (true) {
            System.out.println("--- Admin Menu ---");
            System.out.println("0. Back to main menu");
            System.out.println("1. Create a ferry trip");
            System.out.println("2. View bookings of all ferries and the revenue");
            System.out.println("3. Assign discount to a ferry trip");

            int choice = In.nextInt();
            if (choice == 0) {
                break;
            } else if (choice == 1) { // create a ferry trip
                addFerryTripMenu();
            } else if (choice == 2) { // view bookings of all ferries
                ferryManagement.getFerryTripsData();
            } else if (choice == 3) { // applying discount
                adminApplyDiscountMenu();
            } else {
                System.out.println("Please choose 0, 1, 2, or 3");
            }
        }
    }

    void bookTrip() {
        System.out.println();
        ferryManagement.printAllTripsWithoutCustomer();
        System.out.println("Which trip do you want to book?");
        int tripChoice = In.nextInt();
        FerryTrip tripSelected = ferryManagement.selectTripBasedOnIndex(tripChoice);

        if (ferryManagement.seatAvailable(tripSelected)) {
            System.out.print("Please enter your name: ");
            String name = In.nextLine();

            System.out.print("Please enter you age: ");
            int age = In.nextInt();

            if (age >= 18) {
                System.out.print("Please enter your passport number: ");
                String passNum = In.nextLine();
                AdultCustomer customer = new AdultCustomer(name, age, passNum);
                ferryManagement.bookTrip(customer, tripSelected);
                System.out.println("Booking successful.");
                System.out.println("Your booking has been recorded.");

            } else {
                System.out.println("Please enter your guardian name: ");
                String guardian = In.nextLine();

                ChildCustomer childCustomer = new ChildCustomer(name, age, null);

                if (ferryManagement.setGuardian(guardian, childCustomer)) {
                    ferryManagement.bookTrip(childCustomer, tripSelected);
                    System.out.println("Booking successful.");
                    System.out.println("Your booking has been recorded.");
                } else {
                    System.out.println("Guardian not found in the booking list, please try again.");
                }
            }
        } else {
            System.out.println("Ferry is full.");
        }
    }

    void checkFerryAvailabilityMenu() {
        System.out.println("Date of the trip? (YYYY-MM-DD)");
        String dateInput = In.nextLine();
        try {
            LocalDate date = LocalDate.parse(dateInput);
            System.out.println("Destination?");
            String destination = In.nextLine();
            System.out.println("Starting point?");
            String startingPoint = In.nextLine();
            System.out.println("Would you like to filter by price? (Y/N)");
            String priceFilterInput = In.nextLine();
            if (priceFilterInput.toLowerCase().equals("y")) {
                System.out.println("What is the maximum price you are willing to pay?");
                double priceMaximum = In.nextDouble();
                ArrayList<FerryTrip> availableTrips = ferryManagement.getAvailability(date,
                        destination, startingPoint, priceMaximum);
                if (availableTrips.isEmpty()) {
                    System.out.println(
                            "No ferry trip available for the specified date, destination, and starting point.");
                }
                for (FerryTrip trip : availableTrips) {
                    System.out.println("\n" + trip);
                }
            } else {
                ArrayList<FerryTrip> availableTrips = ferryManagement.getAvailability(date, destination,
                        startingPoint);
                if (availableTrips.isEmpty()) {
                    System.out.println(
                            "No ferry trip available for the specified date, destination, and starting point.");
                }
                for (FerryTrip trip : availableTrips) {
                    System.out.println("\n" + trip);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    void addFerryTripMenu() {
        System.out.println("Current ferry trips:");
        ferryManagement.getFerryTripsData();
        System.out.println(
                "Which ferry do you want to add a trip to (choose the number with 'no trip scheduled' section)?");
        int ferryTarget = In.nextInt();
        FerryTrip ferryTrip = ferryManagement.selectTripBasedOnIndex(ferryTarget);
        if (ferryTrip != null) {
            System.out.println(
                    "The ferry you selected already has a trip scheduled, please select a ferry with 'no trip scheduled' section.");
        } else {
            System.out.println("Where is the starting point of the trip?");
            String startingPoint = In.nextLine();
            System.out.println("Where is the destination of the trip?");
            String destination = In.nextLine();
            System.out.println("What is the price of the trip?");
            double price = In.nextDouble();
            System.out.println("When is the date and time of the trip? (YYYY-MM-DDTHH:MM)");
            String dateTimeInput = In.nextLine();
            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeInput);
                FerryTrip newTrip = new FerryTrip(destination, startingPoint, price, dateTime);
                ferryManagement.setFerryTripList(newTrip, ferryTarget);
                System.out.println("Successfully added ferry trip.");
            } catch (Exception e) {
                System.out.println("Invalid date and time format. Please use YYYY-MM-DDTHH:MM.");
            }
        }
    }

    void adminApplyDiscountMenu() {
        boolean isDiscUnsuccesful = true;

        while (isDiscUnsuccesful) {
            ferryManagement.getFerryTripsData();

            System.out.println("Which trip do you want to add a discount (choose the number)?");
            int tripTarget = In.nextInt();
            FerryTrip selectedTrip = ferryManagement.selectTripBasedOnIndex(tripTarget);

            if (selectedTrip == null) {
                System.out.println("Your selected trip is unavailable, try again.");
            } else {
                while (true) {
                    System.out.println("Is it a fixed amount or percentage?");
                    System.out.println("0. Cancel");
                    System.out.println("1. Fixed amount");
                    System.out.println("2. Percentage");
                    int discChoice = In.nextInt();

                    if (discChoice == 0) {
                        System.out.println("Cancelling applying discount...");
                        break;
                    } else if (discChoice == 1) {
                        System.out.println("How much do you want to apply the discount?");
                        double amount = In.nextDouble();
                        ferryManagement.assignDiscount(amount, selectedTrip);
                        break;
                    } else if (discChoice == 2) {
                        System.out.println("How much to you want to apply the discount?");
                        int percentage = In.nextInt();
                        ferryManagement.assignDiscount(percentage, selectedTrip);
                        break;
                    } else {
                        System.out.println("Please choose 1 or 2");
                    }
                }
            }
        }
    }
}