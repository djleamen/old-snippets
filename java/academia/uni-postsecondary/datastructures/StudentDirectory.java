package datastructures;
// Description: A simple student directory that uses a hash map to store student information.

import java.util.HashMap;

public class StudentDirectory {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(hashValue("Hello"), "Hello");
        map.put(hashValue("hello"), "hello");
        map.put(hashValue("Happy Halloween"), "Happy Halloween");
        System.out.println(map.size());
        System.out.println(map.containsKey(hashValue("HeLlo")));
        System.out.println(map.get(hashValue("Hello")));
        map.put(hashValue("Hello"), "kdfkdsfdmks");
        System.out.println(map.get(hashValue("Hello"))); // overwrites
    }

    public static int hashValue(String s) {
        int hash = s.length();
        for (char c : s.toCharArray()) {
            hash *= (int) c;
        }
        return hash;
    }
}