package datastructures;
// Description: Implementation of Merge Sort algorithm in Java

public class MergeSort {

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            // Divide list
            int mid = (left+right)/2;
            // Recursively sort each side
            mergeSort(array, left, mid);
            mergeSort(array, mid+1, right);
            // Merge sides
            merge(array, left, mid, right);
        }
    }

    // Method to merge two halves of the array
    public static void merge(int[] array, int left, int mid, int right) {
        // Sizes of two subarrays to be merged
        int leftSize = mid + 1 - left;
        int rightSize = right - mid;

        //Temporary arrays
        int[] leftHalf = new int[leftSize];
        int[] rightHalf = new int[rightSize];

        // Copy data to temp arrays
        for (int i = 0; i < leftSize; i++) {
            leftHalf[i] = array[left+i];
        }
        for (int i = 0; i < rightSize; i++) {
            rightHalf[i] = array[mid+1+i];
        }

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of the merged subarray
        int k = left;

        // Merge by taking smallest of two values
        while (i < leftSize && j < rightSize) {
            if (leftHalf[i] < rightHalf[j]) {
                array[k++] = leftHalf[i++];
            } else {
                array[k++] = rightHalf[j++];
            }
        }

        // Take the rest of the left array
        while (i < leftSize) {
            array[k++] = leftHalf[i++];
        }

        // Take the rest of the right away
        while (j < rightSize) {
            array[k++] = rightHalf[j++];
        }

    }

    public static void main(String[] args) {
        int[] array = new int[] {1, 3, 5, 7, 9, 2, 4, 6, 8, 1230, 232, 123, 45, 567, 87, 64, 1, 23};
        mergeSort(array, 0, array.length-1);
        printArray(array);
    }

    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

}
