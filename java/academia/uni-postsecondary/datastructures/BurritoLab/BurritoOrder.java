// Description: This program creates multiple burrito objects and prints them out.

class BurritoOrder {
    public static void main(String[] args) {
        Burrito myBurrito = new Burrito();
        myBurrito.setSize("small");
        myBurrito.setProtein("chicken");
        myBurrito.setRice("white");
        myBurrito.setBeans("no");
        myBurrito.setToppings("lettuce, cheese");
        myBurrito.setGuac(true);

        Burrito myOtherBurrito = new Burrito("large", "steak");
        myOtherBurrito.setRice("brown");
        myOtherBurrito.setBeans("black");
        myOtherBurrito.setToppings("salsa, sour cream, cheese");
        myOtherBurrito.setGuac(false);

        Burrito burritoNumberThree = new Burrito("small", "sofritas", "no", "brown", "lettuce, corn salsa, sour cream", true);
        
        Burrito burritoFour = new Burrito("large", "carnitas", "white", "brown", "lettuce, salsa, corn salsa, sour cream, cheese", true);
        Burrito burritoFive = new Burrito("small", "steak", "no", "no", "lettuce, sour cream", false);
        Burrito burritoSix = new Burrito("small", "chicken", "brown", "black", "salsa", false);

        System.out.println("Burrito 1: " + myBurrito);
        System.out.println("Burrito 2: " + myOtherBurrito);
        System.out.println("Burrito 3: " + burritoNumberThree);
        System.out.println("Burrito 4: " + burritoFour);
        System.out.println("Burrito 5: " + burritoFive);
        System.out.println("Burrito 6: " + burritoSix);
    }
}