// Define a class named InsertionSort that extends the JavaReview class.

/* 
    Introduction:
    Array: a type of variable that can store multiple values
    - All content types must be the same (e.g., all string, all integer)
    What if you wanted to take an existing array and manipulate the order of the elements within it? 
    */

    /*
    Sorting:
    - Allows for the movement or reordering of a value’s index position in an array
    - Makes it easier to find specific values, or organize large collections of data.
    */

    /*
    Example output:
    Original array: 9 2 4 3 15
    Sorted array in ASC order: 1 2 3 4 5 9
    Sorted array in DESC order: 9 5 4 3 2 1
    */

    /*
    Insertion Sort:
    - Basic sorting method
    - Rearranges elements in an array one at a time, left to right
    - More simple than other common sorting methods (e.g., bubble, quick, etc.)
    */

    import java.util.Arrays;
    class InsertionSort {
        public static void main (String[] args) {
            int[] data = {1, 8, 5, 6, 1, 4};
            int size = data.length;
            for (int step = 1; step < size; step++) {
                int key = data[step];
                int j = step - 1;
                while (j >= 0 && key < data[j]) {
                    data[j + 1] = data[j];
                    --j;
                }
                data[j + 1] = key;
            }
            System. out.println("Sorted Array");
            System. out.println(Arrays.toString (data));
        }
    }

    /*
    Applications:
    - Short, small, simple sorting programs
    - Programs that require small value rearrangements
    - To finish or complete the sorting of data that is already semi-sorted (see example on right)
    - Can identify duplicate values in a dataset
    */

    /*
    Remember!
    - For loops are needed to check over each element of the array individually
    - These programs are supposed to be short and simple
    - Insertion sorts aren’t the fastest, but they are more reliable than others
    */