public class App {
    public static void main(String[] args) {
        App app = new App();
        app.runMainMenu();
    }

    void runMainMenu() {
        System.out.println("--- Main Menu ---");
        System.out.println("Are you a customer or admin?");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        System.out.println("0. Exit");

        int choice = In.nextInt();

        while (true) {
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
        System.out.println("---Customer Menu---");
        System.out.println("What would you like to do?");
        System.out.println("0. Back to main menu");
        System.out.println("1. Book a trip");
        System.out.println("2. Check current booking");
        System.out.println("3. Check ferry availability for a trip");

        int choice = In.nextInt();

        while (true) {
            if (choice == 0) {
                System.out.println("Returning to main menu...");
                break;
            }
            else if (choice == 1) {
                
            }
            else if (choice == 2) {

            }
            else if (choice == 3) {

            }
            else if (choice == 4) {
                
            }
            else {
                System.out.println("Please choose 0, 1, 2, 3, or 4,");
            }
        }
    }

    void adminMenu() {
        System.out.println("--- Admin Menu ---");
        System.out.println("0. Back to main menu");
        System.out.println("1. Create a ferry trip");
        System.out.println("2. View bookings of a ferry");
        System.out.println("3. Assign discount to a ferry trip");
    }
}