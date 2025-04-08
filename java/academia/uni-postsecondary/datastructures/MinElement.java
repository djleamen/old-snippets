// Description: This program finds the minimum element in an array using recursion.

public class MinElement {

    public static int minElement(int [] arr, int low, int high) {
        if (low == high) {
            return arr[low];
        } else {
            int mid = (low + high) / 2;
            return Math.min(minElement(arr, low, mid), minElement(arr, mid+1, high));
        }
    }

    public static void main(String[] args) {

        int[] arr = {10, 8, 6, 1, 2, 3, 4, 5, -19349};
        System.out.println(minElement(arr, 0, arr.length-1));

    }


}