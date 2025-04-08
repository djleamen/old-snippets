// Description: This is a simple program that uses a HashMap to store the number of apples in stock. 

import java.util.HashMap;

public class HashMapJava {
    public static void main(String[] args) {
        HashMap<String, Integer> inventory = new HashMap<>();
        inventory.put("Apples", 3);
        System.out.println(inventory.containsKey("Apples"));

        if (inventory.get("Apples") != 0) {
            System.out.println("Number of apples: " + inventory.get("Apples"));
        } else {
            inventory.remove("Apples");
            System.out.println("No more apples in stock sorry :(");
        }

    }
} 