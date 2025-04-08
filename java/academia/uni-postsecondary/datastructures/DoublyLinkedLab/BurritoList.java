// Description: This program creates a doubly linked list of burritos and tests the methods of the Burrito class.

class BurritoOrder {
    public static void main(String[] args) {
        Burrito burritoOne = new Burrito("small", "chicken", "white", "no", "lettuce, cheese", true);
        Burrito burritoTwo = new Burrito("large", "carnitas", "white", "brown", "lettuce, salsa, corn salsa, sour cream, cheese", true);
        Burrito burritoThree = new Burrito("small", "steak", "no", "no", "lettuce, sour cream", false);
        Burrito burritoFour = new Burrito("small", "chicken", "brown", "black", "salsa", false);

        DoublyLinkedList list = new DoublyLinkedList();
        list.addToFront(burritoOne);
        list.addToEnd(burritoTwo);
        list.addToEnd(burritoThree);
        list.addToEnd(burritoFour);
        System.out.println(list);

        System.out.println("\nMaking sure Burrito methods work...");
        burritoOne.setBeans("black");
        System.out.println(burritoOne);
    }
}