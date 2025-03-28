import static org.junit.Assert.assertEquals;
import org.junit.Test;

// TDD: Reverse a String with Special Rules
// Implement a function that reverses a string but keeps numbers in their original
// positions using Test-Driven Development (TDD):
// Red - Write a test (it should fail)
// Green - Implement just enough to pass
// Refactor - Clean up while keeping tests passing
// Repeat - Add another test and continue
// Tasks
// 1. Reverse a simple word (e.g., "hello" -> "olleh")
// 2. Keep numbers in place (e.g., "h3llo" -> "o3llh")
// 3. Handle punctuation (e.g., "h3ll.o" -> "o3ll.h")
// Rules
// Write tests first - No implementation before tests.
// Run tests after each change - Ensure nothing breaks.
// Refactor only after passing tests - Keep improving.

public class TDD {
    // Test Case 0 - Empty String
    @Test
    public void test0() {
        assertEquals("", Main.specialReverse(""));
    }

    // Test Case 1 - Reverse a single letter
    @Test
    public void test1() {
        assertEquals("a", Main.specialReverse("a"));
    }

    // Test Case 2 - Reverse a simple word
    @Test
    public void test2() {
        assertEquals("olleh", Main.specialReverse("hello"));
    }

    // Test Case 3 - Single number
    @Test
    public void test3() {
        assertEquals("3", Main.specialReverse("3"));
    }

    // Test Case 4 - String of numbers
    @Test
    public void test4() {
        assertEquals("123", Main.specialReverse("123"));
    }

    // Test Case 5 - Single punctuation
    @Test
    public void test5() {
        assertEquals(".", Main.specialReverse("."));
    }

    // Test Case 6 - String of punctuation
    @Test
    public void test6() {
        assertEquals(".,!?", Main.specialReverse(".,!?"));
    }

    // Test Case 7 - Keep numbers in place
    @Test
    public void test7() {
        assertEquals("o3llh", Main.specialReverse("h3llo"));
    }

    // Test Case 8 - Handle punctuation
    @Test
    public void test8() {
        assertEquals("o3ll.h", Main.specialReverse("h3ll.o"));
    }
}

class Main {
    public static String specialReverse(String s) {
        char[] arr = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (!Character.isLetter(arr[i])) {
                i++;
            } else if (!Character.isLetter(arr[j])) {
                j--;
            } else {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        System.out.println(specialReverse("h3ll.o"));
    }
}