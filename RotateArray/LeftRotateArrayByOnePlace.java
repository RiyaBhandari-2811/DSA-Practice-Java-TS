public class LeftRotateArrayByOnePlace {

    /**
     * Left rotate an array by one position.
     *
     * Problem:
     * - Given an integer array, rotate it left by one place.
     *   Example: [1, 2, 3, 4, 5] → [2, 3, 4, 5, 1]
     *
     * Idea:
     * - Store the first element in a temporary variable.
     * - Shift all elements one position to the left.
     * - Place the stored first element at the end.
     *
     * Assumptions:
     * - Array can contain duplicates, negative numbers, or mixed values.
     *
     * Edge Cases:
     * - Null array → IllegalArgumentException.
     * - Empty array → no change, return immediately.
     * - Single element array → rotation does nothing.
     *
     * Time Complexity: O(n)
     * - We shift each element exactly once.
     *
     * Space Complexity: O(1)
     * - Only one extra variable is used for temporary storage.
     *
     * Example test cases:
     * - [1, 2, 3, 4, 5] → [2, 3, 4, 5, 1]
     * - [10] → [10]
     * - [] → []
     * - [-1, -2, -3] → [-2, -3, -1]
     * - [5, 5, 5] → [5, 5, 5]
     */
    public static void leftRotateByOne(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array must be non-null");
        }

        int n = arr.length;
        if (n == 0 || n == 1) {
            return; // nothing to rotate
        }

        int first = arr[0];

        // shift elements left
        for (int i = 1; i < n; i++) {
            arr[i - 1] = arr[i];
        }

        arr[n - 1] = first;
    }

    // For demonstration / testing
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        int[][] tests = {
            {1, 2, 3, 4, 5},
            {10},
            {},
            {-1, -2, -3},
            {5, 5, 5}
        };

        String[] names = {
            "case1 [1,2,3,4,5]",
            "case2 [10]",
            "case3 []",
            "case4 [-1,-2,-3]",
            "case5 [5,5,5]"
        };

        for (int i = 0; i < tests.length; i++) {
            System.out.println("Testing " + names[i]);
            int[] arr = tests[i];
            leftRotateByOne(arr);
            printArray(arr);
            System.out.println();
        }
    }
}