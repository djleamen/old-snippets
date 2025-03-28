// Description: Represents the main class for managing the Burrito Shop, handling user interaction and shop operations.

import java.util.Scanner;

/**
 * Represents the main class for managing the Burrito Shop, handling user interaction and shop operations.
 */
public class MyBurritoShopLab {

    public static Scanner keyboard = new Scanner(System.in);

    /**
     * The main method that runs the Burrito Shop simulation.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        BurritoShop shop = new BurritoShop();
        int option = 0;
        while (option != -1) {
            printMenu(shop);
            option = keyboard.nextInt();
            switch (option) {
                case 1: // Take burrito order
                    Burrito order = makeBurrito(); 
                    shop.takeOrder(order); 
                    System.out.println("Order added: " + order);
                    break;
                case 2: // Make burrito order
                    if (shop.ordersWaiting()) {
                        shop.makeOrder();
                    } else {
                        System.out.println("No orders to make.");
                    }
                    break;
                case 3: // Ring out burrito order
                    if (shop.transactionsWaiting() > 0) {
                        shop.ringOut(); 
                    } else {
                        System.out.println("No transactions to ring out.");
                    }
                    break;
                case 4: // Wash a single dish
                    if (!shop.sinkEmpty()) {
                        shop.washDish();
                    } else {
                        System.out.println("No dishes to wash.");
                    }
                    break;
                case 5: // Wash all dishes
                    if (!shop.sinkEmpty()) {
                        shop.washAllDishes(); 
                        System.out.println("All dishes have been washed.");
                    } else {
                        System.out.println("No dishes to wash.");
                    }
                    break;
                case 6: // Close for the day
                    if (shop.canClose()) {
                        option = -1;
                        System.out.println("Great work today!");
                    } else {
                        System.out.println("Cannot close yet, there is still work to be done.");
                    }
                    break;
            }
        }

    }

    /**
     * Creates a new Burrito object based on user input.
     *
     * @return the created Burrito
     */
    public static Burrito makeBurrito() {
        System.out.print("What size [small/large]: ");
        String size = keyboard.next();
        System.out.print("What protein [chicken/steak/carnitas/sofritas/none]: ");
        String protein = keyboard.next();
        System.out.print("What rice [white/brown/none]: ");
        String rice = keyboard.next();
        System.out.print("What beans [black/pinto/none]: ");
        String beans = keyboard.next();
        keyboard.nextLine(); // Consume the newline left-over
        System.out.print("What other toppings (comma list): ");
        String toppings = keyboard.nextLine();
        System.out.print("Do you want guacamole [Y/N]: ");
        boolean guac = keyboard.next().toUpperCase().charAt(0) == 'Y';
        return new Burrito(size, protein, rice, beans, toppings, guac);
    }


    /**
     * Prints the menu for the Burrito Shop, displaying the current state and available options.
     *
     * @param shop the BurritoShop instance to display information for
     */
    public static void printMenu(BurritoShop shop) {
        System.out.println("\nBurrito Shop Task List\n");
        System.out.println("Orders waiting: " + (shop.ordersWaiting() ? "Yes" : "No"));
        System.out.println("Transactions waiting: " + shop.transactionsWaiting() + "\n");
        System.out.println("What task will you do next?");
        System.out.println("   1. Take a Burrito Order");
        System.out.println("   2. Make a Burrito Order");
        System.out.println("   3. Ring Out a Burrito Order");
        System.out.println("   4. Wash Next Dish");
        System.out.println("   5. Wash All Dishes");
        System.out.println("   6. Close For the Day");
        System.out.print("Enter your choice: ");
    }
}
