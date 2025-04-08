// Description: This program calculates the sum of the elements in an array using recursion.

public class SumOfArrays {

    public static int sumElements(int[] arr, int low, int high) {
        if (low == high) {
            return arr[low];
        } else {
            int mid = (low + high) / 2;
            return sumElements(arr, low, mid) + sumElements(arr, mid+1, high);
        }

    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2};
        System.out.println(sumElements(arr, 0, arr.length-1));
    }
    
}