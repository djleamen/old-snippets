// Description: A burrito shop that takes orders, makes orders, rings out customers, and washes dishes.

public class BurritoShop {

    // create queues of orders, transactions, and stack of dishes
    private Queue orders = new Queue();
    private Queue transactions = new Queue();
    private Stack dishes = new Stack();

    // add a burrito to the end of the order queue
    public void takeOrder(Burrito burrito) {
        orders.enqueue(burrito);  
        // add a dirty plate to the dish stack
        dishes.push(new Element("plate")); 
    }

    public void makeOrder() {
        // error if there are no orders to make
        if (orders.isEmpty()) {
            System.out.println("No orders to be made, take more orders.");
        } else {
            // take out the first burrito from the order queue and add it to the transactions queue
            Burrito orderBeingMade = (Burrito) orders.dequeue();
            transactions.enqueue(orderBeingMade);  
            // add a spoon to the dish stack
            dishes.push(new Element("spoon")); 
            System.out.println("Order made: " + orderBeingMade);
        }
    }

    public void ringOut() {
        // error if there are no transactions to ring out
        if (transactions.isEmpty()) {
            System.out.println("No transactions to ring out, make more orders.");
        } else {
            // take out the first burrito from the transaction queue and return its cost
            Burrito burritoSold = (Burrito) transactions.dequeue();  
            System.out.println("Customer was charged: $" + burritoSold.getCost());
        }
    }

    public void washDish() {
        // error if the dish stack is empty
        if (dishes.isEmpty()) {
            System.out.println("No dishes to wash.");
        } else {
            // pop off the first item on the dish stack and return what type of item was washed
            String dishElement = (String) dishes.pop();  
            System.out.println("A " + dishElement + " was washed.");
        }
    }

    public void washAllDishes() {
        // error if the dish stack is empty
        if (dishes.isEmpty()) {
            System.out.println("No dishes to wash.");
        } else {
            // pop off the first item on the dish stack and return what type of item was washed, until the stack is empty
            while (!dishes.isEmpty()) {
                String dishElement = (String) dishes.pop();  
                System.out.println("A " + dishElement + " was washed.");
            }
        }
    }

    // return whether or not there are any dishes in the dish stack (is there anything to wash?)
    public boolean sinkEmpty() {
        return dishes.isEmpty();
    }

    // return whether or not there are any orders in the order queue (is there anything to make?)
    public boolean ordersWaiting() {
        return !orders.isEmpty();
    }

    // return the number of transactions waiting in the transactions queue (how many people to ring out?)
    public int transactionsWaiting() {
        return transactions.getSize();
    }

    // shop can close if there are no items in order queue, transactions queue, and there's nothing in the dish stack
    public boolean canClose() {
        return orders.isEmpty() && transactions.isEmpty() && sinkEmpty();
    }
    
    public static void main(String[] args) {
        BurritoShop shop = new BurritoShop();
        
        // test burritos
        Burrito burrito1 = new Burrito("small", "chicken", "white", "black", "cheese", true);
        Burrito burrito2 = new Burrito("large", "steak", "brown", "pinto", "salsa", false);

        // should add both burritos to the orders queue
        shop.takeOrder(burrito1);
        shop.takeOrder(burrito2);
        System.out.println("Orders waiting: " + shop.ordersWaiting());

        // make first order in queue, should return what order was made
        shop.makeOrder();
        // ring out first transaction in queue, should return how much customer was charged
        shop.ringOut();

        // should be waiting on second burrito
        System.out.println("Orders waiting: " + shop.ordersWaiting());
        shop.makeOrder();
        // should be waiting to ring out second burrito
        System.out.println("Transactions waiting: " + shop.transactionsWaiting());
        shop.ringOut();

        // should just wash a spoon 
        shop.washDish();

        // should return false for indicating there are items in sink
        System.out.println(shop.sinkEmpty());

        // should wash all items in dish stack
        shop.washAllDishes();
    }
}