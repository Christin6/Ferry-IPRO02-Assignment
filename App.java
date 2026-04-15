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

    }

    void adminMenu() {

    }
}