package datastructures;
// Description: Java program to implement Quick Sort, Merge Sort, and Counting Sort algorithms.

public class QuickSortJava {

  // Quick Sort method
  public static void quickSort(int[] array, int low, int high) {
    if (low < high) {
      int pivotIndex = partition(array, low, high);
      quickSort(array, low, pivotIndex - 1); // Recursively sort the left partition
      quickSort(array, pivotIndex + 1, high); // Recursively sort the right partition
    }
  }

  // Partition method for Quick Sort
  private static int partition(int[] array, int low, int high) {
    int pivot = array[high]; // Choosing the last element as the pivot
    int i = low - 1; // Pointer for the smaller element
    for (int j = low; j < high; j++) {
      if (array[j] <= pivot) {
        i++;
        swap(array, i, j); // Swap elements
      }
    }
    swap(array, i + 1, high); // Place the pivot in the correct position
    return i + 1; // Return the pivot index
  }

  // Merge Sort method
  public static void mergeSort(int[] array, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2; // Find the middle point
      mergeSort(array, left, mid); // Recursively sort the first half
      mergeSort(array, mid + 1, right); // Recursively sort the second half
      merge(array, left, mid, right); // Merge the sorted halves
    }
  }

  // Merge method for Merge Sort
  private static void merge(int[] array, int left, int mid, int right) {
    int n1 = mid - left + 1; // Size of left subarray
    int n2 = right - mid; // Size of right subarray

    // Create temporary arrays
    int[] L = new int[n1];
    int[] R = new int[n2];

    // Copy data to temporary arrays
    for (int i = 0; i < n1; i++) {
      L[i] = array[left + i];
    }
    for (int j = 0; j < n2; j++) {
      R[j] = array[mid + 1 + j];
    }

    // Merge the temporary arrays back into the original array
    int i = 0, j = 0; // Initial indices for the subarrays
    int k = left; // Initial index of the merged subarray
    while (i < n1 && j < n2) {
      if (L[i] <= R[j]) {
        array[k] = L[i];
        i++;
      } else {
        array[k] = R[j];
        j++;
      }
      k++;
    }

    // Copy remaining elements of L[], if any
    while (i < n1) {
      array[k] = L[i];
      i++;
      k++;
    }

    // Copy remaining elements of R[], if any
    while (j < n2) {
      array[k] = R[j];
      j++;
      k++;
    }
  }

  // Utility method to swap two elements in an array
  private static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

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


  // Main method
  public static void main(String[] args) {
    
    int [] mAdv = {34, 7, 23, 32, 5, 62, 78, 21, 1, 90, 56, 45, 13, 77, 49, 8, 19, 86, 92, 33};
    int [] qAdv = {41, 3, 52, 26, 38, 57, 9, 49, 5, 77, 33, 60, 18, 24, 85, 12, 67, 42, 6, 71};
    int [] cAdv = {3, 5, 2, 5, 4, 3, 1, 0, 2, 4, 1, 3, 0, 5, 4, 2, 1, 0, 3, 4};

    long start;
    long end;

    // merge best
    start = System.nanoTime();
    mergeSort(mAdv,0,mAdv.length-1);
    end = System.nanoTime();
    long mergeM = end - start;

    start = System.nanoTime();
    quickSort(mAdv, 0, mAdv.length-1);
    end = System.nanoTime();
    long quickM = end - start;

    start = System.nanoTime();
    countingSort(mAdv, 92);
    end = System.nanoTime();
    long countM = end - start;

    // quick best
    start = System.nanoTime();
    mergeSort(qAdv,0,qAdv.length-1);
    end = System.nanoTime();
    long mergeQ = end - start;

    start = System.nanoTime();
    quickSort(qAdv, 0, qAdv.length-1);
    end = System.nanoTime();
    long quickQ = end - start;

    start = System.nanoTime();
    countingSort(qAdv, 85);
    end = System.nanoTime();
    long countQ = end - start;

    // count best
    start = System.nanoTime();
    mergeSort(cAdv,0,cAdv.length-1);
    end = System.nanoTime();
    long mergeC = end - start;

    start = System.nanoTime();
    quickSort(cAdv, 0, cAdv.length-1);
    end = System.nanoTime();
    long quickC = end - start;

    start = System.nanoTime();
    countingSort(cAdv, 5);
    end = System.nanoTime();
    long countC = end - start;


    System.out.println("Merge advantage:");
    System.out.println("Merge Sort: " + mergeM);
    System.out.println("Quick Sort: " + quickM);
    System.out.println("Counting Sort: " + countM);

    System.out.println("\nQuick advantage:");
    System.out.println("Merge Sort: " + mergeQ);
    System.out.println("Quick Sort: " + quickQ);
    System.out.println("Counting Sort: " + countQ);
    
    System.out.println("\nCounting advantage:");
    System.out.println("Merge Sort: " + mergeC);
    System.out.println("Quick Sort: " + quickC);
    System.out.println("Counting Sort: " + countC);
  }
}
