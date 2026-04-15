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
    }

    void customerMenu() {

    }

    void adminMenu() {
        System.out.println("--- Admin Menu ---");
        System.out.println("0. Back to main menu");
        System.out.println("1. Create a ferry trip");
        System.out.println("2. View bookings of a ferry");
        System.out.println("3. Assign discount to a ferry trip");
    }
}