// Description: This program implements the counting sort algorithm to sort an array of integers.

public class CountingSortJava {

  public static void countingSort(int[] arr, int maxValue) {
    int min = findMin(arr);
    maxValue -= min;
    shiftArray(arr, -min);

    int[] count = new int[maxValue + 1];
    int[] sorted = new int[arr.length];

    for (int i : arr) {
      count[i]++;
    }

    for (int i = 1; i < count.length; i++) {
      count[i] += count[i - 1];
    }

    for (int i = arr.length - 1; i >= 0; i--) {
      int value = arr[i];
      count[value]--;
      int index = count[value];
      sorted[index] = value;
    }

    for (int i = 0; i < sorted.length; i++) {
      arr[i] = sorted[i] + min; // Shift back to original values
    }
  }

  public static int findMin(int[] arr) {
    int min = Integer.MAX_VALUE;
    for (int i : arr) {
      min = Math.min(min, i);
    }
    return min;
  }

  public static void shiftArray(int[] arr, int shift) {
    for (int i = 0; i < arr.length; i++) {
      arr[i] += shift;
    }
  }

  // Test method
  public static void main(String[] args) {
    int[] arr = {-4, 2, 3, -6, 5, -2, 4, 1};
    int maxValue = 5;

    System.out.println("Original array:");
    printArray(arr);

    countingSort(arr, maxValue);

    System.out.println("Sorted array:");
    printArray(arr);
  }

  // Helper method to print an array
  private static void printArray(int[] arr) {
    for (int num : arr) {
      System.out.print(num + " ");
    }
    System.out.println();
  }
}