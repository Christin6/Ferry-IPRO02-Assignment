import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class App {
    static FerryManagement ferryManagement = new FerryManagement();

    public static void main(String[] args) {
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
            System.out.println();
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
            System.out.println();
            System.out.println("---Customer Menu---");
            System.out.println("What would you like to do?");
            System.out.println("0. Back to main menu");
            System.out.println("1. Book a trip");
            System.out.println("2. Check current booking");
            System.out.println("3. Check ferry availability for a trip");

            int choice = In.nextInt();

            if (choice == 0) {
                System.out.println();
                System.out.println("Returning to main menu...");
                break;
            } else if (choice == 1) {
                bookTripMenu();
            } else if (choice == 2) {
                checkCurrentBookingMenu();
            } else if (choice == 3) {
                checkFerryAvailabilityMenu();
            } else {
                System.out.println("Please choose 0, 1, 2, or 3");
            }
        }
    }

    void adminMenu() {
        while (true) {
            System.out.println();
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
                ferryDataMenu();
            } else if (choice == 3) { // applying discount
                adminApplyDiscountMenu();
            } else {
                System.out.println("Please choose 0, 1, 2, or 3");
            }
        }
    }

    void ferryDataMenu() {
        while (true) {
            System.out.println("How would you like to view the data?");
            System.out.println("0. Back to main menu");
            System.out.println("1. Based on current revenue (lowest to highest)");
            System.out.println("2. Based on current revenue (highest to highest)");
            System.out.println("3. Based on ferry ship, including non-active ferries");
            int choice = In.nextInt();

            if (choice == 0) {
                System.out.println("Exiting to main menu...");
                break;
            }
            else if (choice == 1) {
                ferryManagement.getFerryTripsDataSorted(FerryManagement.compareByPriceAsc);
            }
            else if (choice == 2) {
                ferryManagement.getFerryTripsDataSorted(FerryManagement.compareByPriceDesc);
            }
            else if (choice == 3) {
                ferryManagement.getFerryTripsData();
            }
            else {
                System.out.println("Please choose option 0, 1, 2, or 3");
            }
        }   
    }

    void checkCurrentBookingMenu() {
        System.out.println();
        System.out.println("Enter your assigned name in the booking: ");
        String name = In.nextLine();
        ArrayList<FerryTrip> bookingList = ferryManagement.customerBookedTrip(name);
        if (bookingList.isEmpty()) {
            System.out.println("No booking has been made under the name " + name);
            return;
        }
        System.out.println("All of your current bookings:");
        for (FerryTrip trip : bookingList) {
            System.out.println("-) " + trip.getStartingPoint()
                    + " to " + trip.getDestination()
                    + " (Price: $" + trip.getPrice() + ")");
        }
    }

    MedicalCondition handleMedicalCondition(){
        MedicalCondition medicalCondition = MedicalCondition.HEALTHY;
        while (true){
            System.out.println("Do you have any listed medical condition: ");
            System.out.println("1. SEA_SICK");
            System.out.println("2. PREGNANT");
            System.out.println("3. SPECIAL DISABILITY");
            System.out.println("4. NONE");
            int choice = In.nextInt();
            if (choice == 1){
                medicalCondition = MedicalCondition.SEA_SICK;
                break;
            } else if (choice == 2){
                medicalCondition = MedicalCondition.PREGNANT;
                break;
            } else if (choice == 3){
                medicalCondition = MedicalCondition.SPECIAL_DISABILITY;
                break;
            } else if (choice == 4) {
                medicalCondition = MedicalCondition.HEALTHY;
                break;
            } else {
                System.out.println("Invalid choice ");
            }
        }
        return medicalCondition;
    }

    void bookTripMenu() {
        System.out.println();
        ferryManagement.printAllTrips();
        System.out.println("Which trip do you want to book?");
        int tripChoice = In.nextInt();

        FerryTrip tripSelected = ferryManagement.selectTripBasedOnIndex(tripChoice);

        try {
            if (ferryManagement.seatAvailable(tripSelected)) {
                System.out.println();
                System.out.print("Please enter your name: ");
                String name = In.nextLine();

                if (ferryManagement.findCustomerByName(name) != null) {
                    System.out.println("A booking has already been made under the name " + name
                            + ", choose an option:");
                    System.out.println("1. Book another trip under the same name");
                    System.out.println("2. Cancel booking");
                    int bookingChoice = In.nextInt();

                    if (bookingChoice == 1) {
                        System.out.println("Booking another trip under the same name...");
                        Customer customer = ferryManagement.findCustomerByName(name);
                        ferryManagement.bookTrip(customer, tripSelected);
                        return;
                    } else if (bookingChoice == 2) {
                        System.out.println("Cancelling booking...");
                        return;
                    } else {
                        System.out.println("Invalid input, cancelling booking...");
                        return;
                    }

                }
                System.out.print("Please enter you age: ");
                int age = In.nextInt();

                if (age >= 18) {
                    System.out.print("Please enter your passport number: ");
                    String passNum = In.nextLine();
                    
                    AdultCustomer customer = new AdultCustomer(name, age, passNum, handleMedicalCondition());
                    ferryManagement.bookTrip(customer, tripSelected);
                    System.out.println("Booking successful.");
                    System.out.println("Your booking has been recorded.");

                } else {
                    System.out.println("Please enter your guardian name: ");
                    String guardian = In.nextLine();

                    ChildCustomer childCustomer = new ChildCustomer(name, age, null, handleMedicalCondition());

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
        } catch (NullPointerException e) {
            System.err.println("You can't choose an unregistered trip.");
        } catch (ClassCastException c) {
            System.out.println("Guardian not found in the booking list, please try again.");
        }
    }

    void checkFerryAvailabilityMenu() {
        System.out.println();
        System.out.println("Destination?");
        String destination = In.nextLine();
        System.out.println("Starting point?");
        String startingPoint = In.nextLine();
        System.out.println("Would you like to filter by price? (Y/N)");
        String priceFilterInput = In.nextLine();
        if (priceFilterInput.toLowerCase().equals("y")) {
            System.out.println("What is the maximum price you are willing to pay?");
            double priceMaximum = In.nextDouble();
            ArrayList<FerryTrip> availableTrips = ferryManagement.getAvailability(destination, startingPoint,
                    priceMaximum);
            if (availableTrips.isEmpty()) {
                System.out.println(
                        "No ferry trip available.");
            }
            for (FerryTrip trip : availableTrips) {
                System.out.println("\n-) " + trip.getDestination() + " to " + trip.getStartingPoint() + " (Price: $" + trip.getPrice() + ")");
            }
        } else {
            ArrayList<FerryTrip> availableTrips = ferryManagement.getAvailability(destination, startingPoint);
            if (availableTrips.isEmpty()) {
                System.out.println(
                        "No ferry trip available.");
            }
            for (FerryTrip trip : availableTrips) {
                System.out.println("\n-) " + trip.getDestination() + " to " + trip.getStartingPoint() + " (Price: $" + trip.getPrice() + ")");
            }
        }
    }

    void addFerryTripMenu() {
        System.out.println();
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
            FerryTrip newTrip = new FerryTrip(destination, startingPoint, price);
            ferryManagement.setFerryTripList(newTrip, ferryTarget);
            System.out.println("Successfully added ferry trip.");
        }
    }

    void adminApplyDiscountMenu() {
        boolean isDiscUnsuccesful = true;

        while (isDiscUnsuccesful) {
            ferryManagement.getFerryTripsData();

            System.out.println("Which trip do you want to add a discount (choose the number)?");
            System.out.println("Type 0 to go back to the main menu");
            int tripTarget = In.nextInt();

            if (tripTarget == 0) {
                isDiscUnsuccesful = false;
            }

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