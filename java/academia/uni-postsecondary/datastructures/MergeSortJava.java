package datastructures;
// Description: Merge sort implementation in Java.

public class MergeSortJava {

    public static void mergeSort(String[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static void merge(String[] array, int left, int mid, int right) {
        int leftSize = mid + 1 - left;
        int rightSize = right - mid;

        String[] leftHalf = new String[leftSize];
        String[] rightHalf = new String[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftHalf[i] = array[left + i];
        }
        for (int i = 0; i < rightSize; i++) {
            rightHalf[i] = array[mid + 1 + i];
        }

        int i = 0, j = 0;

        int k = left;

        while (i < leftSize && j < rightSize) {
            if (leftHalf[i].compareTo(rightHalf[j]) <= 0) {
                array[k++] = leftHalf[i++];
            } else {
                array[k++] = rightHalf[j++];
            }
        }

        while (i < leftSize) {
            array[k++] = leftHalf[i++];
        }

        while (j < rightSize) {
            array[k++] = rightHalf[j++];
        }
    }

    public static void main(String[] args) {
        String[] array = {"hello", "cat", "dog", "meow", "bark", "purrrrr"};
        mergeSort(array, 0, array.length - 1);
        printArray(array);
    }

    public static void printArray(String[] array) {
        for (String s : array) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}