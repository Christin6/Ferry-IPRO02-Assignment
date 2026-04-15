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
        int choice = In.nextInt();
        
        while (true) {
            if (choice == 1) {
                customerMenu();
            }
            else if (choice == 2) {
                adminMenu();
            }
            else {
                System.out.println("Please choose 1 or 2");
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
                break;
            }
        }
    }

    void adminMenu() {

    }
}