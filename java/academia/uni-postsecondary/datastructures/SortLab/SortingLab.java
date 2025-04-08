// Description: This program sorts an array of Burritos and a LinkedList of Burritos by size and protein.

import java.util.LinkedList;

public class SortingLab {

    // print an array of Burritos
    public static void printArray(Burrito[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("[]");
            return;
        }
        // Use StringBuilder to make it easier lol
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            Burrito burrito = arr[i];
            output.append(burrito.getSize()).append(" ").append(burrito.getProtein());
            if (i < arr.length - 1) {
                output.append(", ");
            }
        }
        output.append("]");
        System.out.println(output);
    }

    // print a LinkedList of Burritos
    public static void printList(LinkedList<Burrito> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("[]");
            return;
        }
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            Burrito burrito = list.get(i);
            output.append(burrito.getSize()).append(" ").append(burrito.getProtein());
            if (i < list.size() - 1) {
                output.append(", ");
            }
        }
        output.append("]");
        System.out.println(output);
    }
     
    // sort array of burritos
    public static Burrito[] sortBurritos(Burrito[] arr) {
        if (arr == null || arr.length == 0) {
            return new Burrito[0];
        }
        return mergeSort(arr);
    }

    // sort list of burritos
    public static LinkedList<Burrito> sortBurritos(LinkedList<Burrito> list) {
        if (list == null || list.size() <= 1) {
            return new LinkedList<>(list);
        }
        return quickSort(list);
    }

    // merge sort on array of burritos
    private static Burrito[] mergeSort(Burrito[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int mid = arr.length/2;
        Burrito[] left = new Burrito[mid];
        Burrito[] right = new Burrito[arr.length - mid];
        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        return merge(mergeSort(left), mergeSort(right));
    }

    // quick sort on list of burritos
    private static LinkedList<Burrito> quickSort(LinkedList<Burrito> list) {
        if (list.size() <= 1) {
            return list;
        }

        Burrito pivot = list.getFirst();
        LinkedList<Burrito> less = new LinkedList<>();
        LinkedList<Burrito> equal = new LinkedList<>();
        LinkedList<Burrito> greater = new LinkedList<>();

        for (Burrito burrito : list) {
            int comparison = compareBurritos(burrito, pivot);
            if (comparison < 0) {
                less.add(burrito);
            } else if (comparison == 0) {
                equal.add(burrito);
            } else {
                greater.add(burrito);
            }
        }

        // put all sorted components together
        LinkedList<Burrito> sorted = new LinkedList<>();
        sorted.addAll(quickSort(less));
        sorted.addAll(equal);
        sorted.addAll(quickSort(greater));
        return sorted;
    }

    // merge function for merge sort
    private static Burrito[] merge(Burrito[] left, Burrito[] right) {
        Burrito[] result = new Burrito[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            int comparison = compareBurritos(left[i], right[j]);
            if (comparison <= 0) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    // compare burritos in order to sort by size and protein
    private static int compareBurritos(Burrito burrito1, Burrito burrito2) {
        if (!burrito1.getSize().equals(burrito2.getSize())) {
            return burrito1.getSize().equals("large") ? -1 : 1;
        }

        return burrito1.getProtein().compareTo(burrito2.getProtein());
    }

    // main to test printArray and printList methods
    public static void main(String[] args) {

        // array of Burritos
        Burrito[] burritoArray = {
            new Burrito("small", "chicken"),
            new Burrito("large", "steak"),
            new Burrito("large", "chicken"),
            new Burrito("small", "sofritas"),
            new Burrito("small", "chicken")
        };

        // LinkedList of Burritos
        LinkedList<Burrito> burritoList = new LinkedList<>();
        burritoList.add(new Burrito("small", "chicken"));
        burritoList.add(new Burrito("large", "steak"));
        burritoList.add(new Burrito("large", "chicken"));
        burritoList.add(new Burrito("small", "sofritas"));
        burritoList.add(new Burrito("small", "chicken"));

        // print original array and list
        System.out.print("Original Array: ");
        printArray(burritoArray);
        System.out.print("Original List: ");
        printList(burritoList);

        // sort array and list
        Burrito[] sortedArray = sortBurritos(burritoArray);
        LinkedList<Burrito> sortedList = sortBurritos(burritoList);

        // print the sorted array and list
        System.out.println("Sorted Array: ");
        printArray(sortedArray);
        System.out.println("Sorted List: ");
        printList(sortedList);

        // edge case testing
        Burrito[] emptyArray = {};
        Burrito[] singleItemArray = { new Burrito("large", "carnitas") };
        Burrito[] duplicateArray = {
            new Burrito("small", "chicken"),
            new Burrito("small", "chicken"),
            new Burrito("small", "chicken")
        };

        LinkedList<Burrito> emptyList = new LinkedList<>();
        LinkedList<Burrito> singleItemList = new LinkedList<>();
        singleItemList.add(new Burrito("large", "carnitas"));
        LinkedList<Burrito> duplicateList = new LinkedList<>();
        duplicateList.add(new Burrito("small", "chicken"));
        duplicateList.add(new Burrito("small", "chicken"));
        duplicateList.add(new Burrito("small", "chicken"));

        System.out.print("\nEmpty Array: ");
        printArray(sortBurritos(emptyArray));
        System.out.print("Single Item Array: ");
        printArray(sortBurritos(singleItemArray));
        System.out.print("Duplicate Array: ");
        printArray(sortBurritos(duplicateArray));

        System.out.print("Empty List: ");
        printList(sortBurritos(emptyList));
        System.out.print("Single Item List: ");
        printList(sortBurritos(singleItemList));
        System.out.print("Duplicate List: ");
        printList(sortBurritos(duplicateList));
    }
}

